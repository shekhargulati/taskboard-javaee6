'use strict';

var taskboardModule = angular.module("taskboard", ["taskboard.services"]);

taskboardModule.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'views/taskboard/list.html',
            controller: 'taskboardListController',
            resolve: {
                taskboards: ['TaskboardListLoader', function (TaskboardListLoader) {
                    return TaskboardListLoader();
                }]
            }
        }).
        when('/taskboards/new', {
            templateUrl: 'views/taskboard/create.html',
            controller: 'taskboardCreateController'
        }).
        when('/taskboards/:taskboardId', {
            templateUrl: 'views/taskboard/detail.html',
            controller: 'taskboardDetailController',
            resolve: {
                taskboard: ['TaskboardLoader', function (TaskboardLoader) {
                    return TaskboardLoader();
                }]
            }
        }).
        when('/taskboards/:taskboardId/tasks/new', {
            templateUrl: 'views/task/create.html',
            controller: 'taskCreateController'
        }).
        otherwise({
            redirectTo: '/'
        });
}]);

taskboardModule.filter('summary', function () {
    var summaryFilter = function (input) {
        var words = input.trim().split(' ');
        var wordCount = words.length;
        if (wordCount > 10) {
            words = words.slice(0, 10);
            var summary = words.join(' ');
            summary = summary.concat('...');
            return summary;
        }
        return input;
    }
    return summaryFilter;
});

taskboardModule.controller('taskboardListController', function ($scope, taskboards) {
    $scope.taskboards = taskboards;
});

taskboardModule.controller('taskboardCreateController', ['$scope', '$location', 'Taskboard',
    function ($scope, $location, Taskboard) {
        $scope.taskboard = new Taskboard({});

        $scope.save = function () {
            $scope.taskboard.$save(function (taskboard) {
                $location.path('/');
            });
        };
    }]);


taskboardModule.controller('taskboardDetailController', function ($scope, $location, taskboard) {
    $scope.taskboard = taskboard;
});


taskboardModule.controller('taskCreateController', ['$scope', '$location', '$routeParams', 'Task',
    function ($scope, $location, $routeParams, Task) {
        var taskboardId = $routeParams.taskboardId;
        $scope.task = new Task({taskboardId:taskboardId});

        $scope.save = function () {
            var task = $scope.task;
            if (!(task['tags'] instanceof Array)) {
                task['tags'] = task['tags'].split(",");
                $scope.task = task;
            }
            $scope.task.$save(function (task) {
                toastr.success("Created New Task");
                $location.path('/taskboards/' + taskboardId);
            });
        };
    }]
);