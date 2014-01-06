var services = angular.module("taskboard.services", ["ngRoute", "ngResource"]);

services.factory('Taskboard', function ($resource) {
        return  $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId', {taskboardId:'@id'});
    }
);

services.factory('TaskboardListLoader', ['Taskboard', '$q',
    function (Taskboard, $q) {
        return function () {
            var delay = $q.defer();
            Taskboard.query(function (taskboards) {
                delay.resolve(taskboards);
            }, function () {
                delay.reject('Unable to fetch taskboards');
            });
            return delay.promise;
        };
    }]
);

services.factory('TaskboardLoader', ['Taskboard', '$route', '$q',
    function (Taskboard, $route, $q) {
        return function () {
            var delay = $q.defer();
            Taskboard.get({taskboardId: $route.current.params.taskboardId}, function (taskboard) {
                delay.resolve(taskboard);
            }, function () {
                delay.reject('Unable to find taskboard with id: ' + $route.current.params.taskboardId);
            });
            return delay.promise;
        };
    }]
);

services.factory('Task',
    function ($resource, $routeParams) {
        var taskboardId = $routeParams.taskboardId;
        var task = $resource('http://taskboard-shekhargulati.rhcloud.com/api/v1/taskboards/:taskboardId/tasks/:taskId', {taskboardId: taskboardId, taskId: "@id"});
        task.prototype.isNew = function () {
            return (typeof(this.id) === 'undefined');
        }
        return task;
    }
);

services.factory('TaskListLoader', ['Task', '$q',
    function (Task, $q) {
        return function () {
            var delay = $q.defer();
            Task.query(function (tasks) {
                delay.resolve(tasks);
            }, function () {
                delay.reject('Unable to fetch tasks');
            });
            return delay.promise;
        };
    }]
);

services.factory('TaskLoader', ['Task', '$route', '$q',
    function (Task, $route, $q) {
        return function () {
            var delay = $q.defer();
            Task.get({taskId: $route.current.params.taskId}, function (task) {
                delay.resolve(task);
            }, function () {
                delay.reject('Unable to find task with id: ' + $route.current.params.taskId);
            });
            return delay.promise;
        };
    }]
);