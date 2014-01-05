angular.module("taskboard.services", ["ngRoute","ngResource"]).
    factory('taskboard', function ($resource) {
        var taskboard = $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId', {taskboardId: '@id'});
        taskboard.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return taskboard;
    }).
    factory('task',function($resource,$routeParams){
        var taskboardId = $routeParams.taskboardId;
        var task = $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId/tasks/:taskId', {taskboardId: taskboardId,taskId:"@id"});
        task.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return task;
    });

angular.module("taskboard", ["taskboard.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'views/taskboard/list.html', controller: taskboardListController})
            .when('/taskboards/new', {templateUrl: 'views/taskboard/create.html', controller: taskboardCreateController})
            .when('/taskboards/:taskboardId', {templateUrl: 'views/taskboard/detail.html', controller: taskboardDetailController})
            .when('/taskboards/:taskboardId/tasks/new',{templateUrl: 'views/task/create.html', controller: taskCreateController});
    });




function taskboardListController($scope, taskboard) {
    $scope.taskboards = taskboard.query();


}

function taskboardCreateController($scope, $routeParams, $location, taskboard) {

    $scope.taskboard = new taskboard();

    $scope.save = function () {
    	$scope.taskboard.$save(function (taskboard, headers) {
    		toastr.success("Created New taskboard");
            $location.path('/');
        });
    };
}


function taskboardDetailController($scope, $routeParams, $location, taskboard) {
    var taskboardId = $routeParams.taskboardId;

    $scope.taskboard = taskboard.get({taskboardId: taskboardId});

}


function taskCreateController($scope, $routeParams, $location, task) {
    var taskboardId = $routeParams.taskboardId;

    $scope.task = new task();

    $scope.save = function () {
        var task = $scope.task;
        if(!(task['tags'] instanceof Array)){
            task['tags'] = task['tags'].split(",");
            $scope.task = task;
        }
        $scope.task.$save(function (task, headers) {
            toastr.success("Created New Task");
            $location.path('/taskboards/'+taskboardId);
        });
    };
}
