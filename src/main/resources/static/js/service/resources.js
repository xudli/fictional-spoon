angular.module('mainApp.services', []).factory('userService', function($resource) {

    return $resource('/api/user/:uuid', {uuid:'@uuid'});;
})