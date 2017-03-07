mainApp
    .config(function($routeProvider) {

        $routeProvider.when('/permission', {
            templateUrl : '../../templates/permission/list.html',
            controller : 'permissionListController'
        });
    });
angular.module('mainApp.permissionControllers',[])
	.controller('permissionListController',
			function($scope,$http,permissionService){
		$scope.permissions = permissionService.query();
	});

