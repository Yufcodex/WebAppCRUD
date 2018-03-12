'use strict';

angular.module('WebCrudTest').factory('UserService',
    ['$http', '$q',
        function ($http, $q) {

            var factory = {
                createUser: createUser,
                loadUser: loadUser,
                getAllUsers : getAllUsers,
                editUser : editUser,
                removeUser : removeUser,
            };

            return factory;

            function loadUser(objParam) {
                var deferred = $q.defer();
                $http.get('/auth/'+objParam.mail+"/and/"+objParam.pass)
                    .then(
                        function (response) {
                            console.log('call sucecss');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('call error');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }      

            function createUser(objParam) {
                var deferred = $q.defer();
                $http.post('/auth/'+objParam.mail+","+objParam.name+","+objParam.pass)
                    .then(
                        function (response) {
                            console.log('call sucecss');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('call error');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers() {
                var deferred = $q.defer();
                $http.get('/auth/')
                    .then(
                        function (response) {
                            console.log('call sucecss');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('call error');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function editUser(objParam) {
                var deferred = $q.defer();
                $http.put('/auth/'+objParam.mail+","+objParam.name+","+objParam.pass)
                    .then(
                        function (response) {
                            console.log('call sucecss');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.log('call error');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(mail) {
                var deferred = $q.defer();
                $http.delete('/auth/'+mail)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
]);