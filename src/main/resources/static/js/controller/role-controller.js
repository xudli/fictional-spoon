mainApp
    .config(function($routeProvider) {

        $routeProvider.when('/role', {
            templateUrl : '../../templates/role/list.html',
            controller : 'roleListController'
        });
    });
angular.module('mainApp.roleControllers',[])
	.controller('roleListController',
			function($scope,$http,roleService){
		$scope.roles = roleService.query();
	});

