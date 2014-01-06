'use strict';

var taskboardModule = angular.module("taskboard", ["taskboard.services"]);

function taskboardRouteConfig($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'views/taskboard/list.html',
            controller: taskboardModule.taskboardListController,
            resolve: {
                taskboards: ['TaskboardListLoader', function (TaskboardListLoader) {
                    return new TaskboardListLoader();
                }]
            }
        }).
        when('/taskboards/new', {
            templateUrl: 'views/taskboard/create.html',
            controller: taskboardModule.taskboardCreateController,
            resolve: {
                taskboard: ['TaskboardLoader', function (TaskboardLoader) {
                    return new TaskboardLoader();
                }]
            }
        }).
        when('/taskboards/:taskboardId', {
            templateUrl: 'views/taskboard/detail.html',
            controller: taskboardModule.taskboardDetailController,
            resolve: {
                taskboard: ['TaskboardLoader', function (TaskboardLoader) {
                    return new TaskboardLoader();
                }]
            }
        }).
        when('/taskboards/:taskboardId/tasks/new', {
            templateUrl: 'views/task/create.html',
            controller: taskboardModule.taskCreateController,
            resolve: {
                task: ['TaskLoader', function (TaskLoader) {
                    return new TaskLoader();
                }]
            }
        }).
        otherwise({
            redirectTo: '/'
        });
}

taskboardModule.config(taskboardRouteConfig);

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

taskboardModule.taskboardListController = function ($scope, taskboards) {
    $scope.taskboards = taskboards;
}

taskboardModule.taskboardCreateController = function ($scope, $routeParams, $location, taskboard) {
    $scope.taskboard = taskboard;
    $scope.save = function () {
        $scope.taskboard.$save(function (taskboard, headers) {
            toastr.success("Created New taskboard");
            $location.path('/');
        });
    };
}
taskboardModule.taskboardDetailController = function ($scope, $location, taskboard) {
    $scope.taskboard = taskboard;
}


taskboardModule.taskCreateController = function ($scope, $routeParams, $location, task) {
    var taskboardId = $routeParams.taskboardId;
    $scope.task = task;
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
