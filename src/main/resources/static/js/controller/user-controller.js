mainApp
    .config(function($routeProvider) {

        $routeProvider.when('/', {
            templateUrl : '../../templates/user/list.html',
            controller : 'userListController'
        });
    });

angular.module('mainApp.controllers', [])
	.controller('mainController', function() {
	

	})
	.controller('userListController',
		function($scope,$http,userService) {

		$scope.users = userService.query();

	});
