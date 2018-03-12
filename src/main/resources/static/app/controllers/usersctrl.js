'use strict';

angular.module('WebCrudTest')
.controller("usersCtrl", ['$scope', '$http', '$location', '$localStorage', 'UserService',
    function($scope, $http, $location, $localStorage, UserService){

        $scope.successMessage = '';
        $scope.errorMessage = '';

        function getUsers(){
            UserService.getAllUsers().then(
                function(response){
                    if(response != null || response != undefined)
                    {
                        $scope.allUsersObj = response;
                        console.log($scope.allUsers);
                    }
                }
            );
        }

        $scope.editUser = function(mail, pass) {
            $scope.successMessage='';
            $scope.errorMessage='';
            var usrObj = {}
            usrObj.mail = mail;
            usrObj.pass = pass;
            UserService.loadUser(usrObj).then(
                function (user) {
                    $scope.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + mail + ', Error :' + errResponse.data);
                }
            );
        }

        $scope.editUserInfo = function(mail, name, pass){
            var editObj = {};
            editObj.mail = mail;
            editObj.name = name;
            editObj.pass = pass;
            UserService.editUser(editObj).then(
                function(response){
                    $scope.allUsersObj = response;
                    console.log('User updated successfully');
                },
                function(errResponse){
                    console.error('Error while updating user');
                }
            );

        }

        $scope.removeUser = function(mail){
            console.log('About to remove User with mail '+mail);
            UserService.removeUser(mail).then(
                function(response){
                    $scope.allUsersObj = response;
                    console.log('User removed successfully');
                },
                function(errResponse){
                    console.error('Error while removing user');
                }
            );
        }

        getUsers();
    }
]);