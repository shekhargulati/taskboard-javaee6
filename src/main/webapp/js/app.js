angular.module("taskboard.services", ["ngResource"]).
    factory('taskboard', function ($resource) {
        var taskboard = $resource('/api/v1/taskboards/:taskboardId', {taskboardId: '@id'});
        taskboard.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return taskboard;
    });

angular.module("taskboard", ["taskboard.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: 'views/taskboard/list.html', controller: taskboardListController})
            .when('/taskboards/new', {templateUrl: 'views/taskboard/create.html', controller: taskboardCreateController})
            .when('/taskboards/:taskboardId', {templateUrl: 'views/taskboard/detail.html', controller: taskboardDetailController});
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
