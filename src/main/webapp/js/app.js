var taskboardServicesModlule = angular.module("taskboard.services", ["ngRoute", "ngResource"]);

taskboardServicesModlule.factory('Taskboard', function ($resource) {
    var taskboard = $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId', {taskboardId: '@id'});
    taskboard.prototype.isNew = function () {
        return (typeof(this.id) === 'undefined');
    }
    return taskboard;
});

taskboardServicesModlule.factory('Task', function ($resource, $routeParams) {
    var taskboardId = $routeParams.taskboardId;
    var task = $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId/tasks/:taskId', {taskboardId: taskboardId, taskId: "@id"});
    task.prototype.isNew = function () {
        return (typeof(this.id) === 'undefined');
    }
    return task;
});

var taskboardModule = angular.module("taskboard", ["taskboard.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'views/taskboard/list.html', controller: taskboardModule.taskboardListController})
            .when('/taskboards/new', {templateUrl: 'views/taskboard/create.html', controller: taskboardModule.taskboardCreateController})
            .when('/taskboards/:taskboardId', {templateUrl: 'views/taskboard/detail.html', controller: taskboardModule.taskboardDetailController})
            .when('/taskboards/:taskboardId/tasks/new', {templateUrl: 'views/task/create.html', controller: taskboardModule.taskCreateController});
    });


taskboardModule.taskboardListController = function ($scope, Taskboard) {
    $scope.taskboards = Taskboard.query();
}

taskboardModule.taskboardCreateController = function ($scope, $routeParams, $location, Taskboard) {
    $scope.taskboard = new Taskboard();
    $scope.save = function () {
        $scope.taskboard.$save(function (taskboard, headers) {
            toastr.success("Created New taskboard");
            $location.path('/');
        });
    };
}


taskboardModule.taskboardDetailController = function ($scope, $routeParams, $location, Taskboard) {
    var taskboardId = $routeParams.taskboardId;
    $scope.taskboard = Taskboard.get({taskboardId: taskboardId});
}


taskboardModule.taskCreateController = function ($scope, $routeParams, $location, Task) {
    var taskboardId = $routeParams.taskboardId;
    $scope.task = new Task();
    $scope.save = function () {
        var task = $scope.task;
        if (!(task['tags'] instanceof Array)) {
            task['tags'] = task['tags'].split(",");
            $scope.task = task;
        }
        $scope.task.$save(function (task, headers) {
            toastr.success("Created New Task");
            $location.path('/taskboards/' + taskboardId);
        });
    };
}
