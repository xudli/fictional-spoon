var app = angular.module('mainApp.services', []);
app.factory('userService', function($resource) {

    return $resource('/api/user/:uuid', {uuid:'@uuid'});;
});

app.factory('permissionService', function($resource) {

    return $resource('/api/permission/:uuid', {uuid:'@uuid'});;
});

app.factory('roleService', function($resource) {

    return $resource('/api/role/:uuid', {uuid:'@uuid'});;
});