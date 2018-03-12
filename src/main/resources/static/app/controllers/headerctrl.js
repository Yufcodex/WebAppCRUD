'use strict';

angular.module('WebCrudTest')
.controller("headerCtrl", ['$scope', '$http', '$location', '$localStorage', 'UserService',
    function($scope, $http, $location, $localStorage, UserService){
    	$scope.titleArea = 'Bienvenido (CRUD Simple)';
        $scope.userLoged = 'Iniciar sesión';

        function getUserLogin(){
            if($localStorage.usrLoged == undefined || $localStorage.usrLoged == "")
            {
                var newpath = window.location.origin; 
                console.log(newpath);            
                window.location = newpath;  
                alert('No has iniciado sesión');                 
            }
            else
            {
                $scope.userLoged = $localStorage.usrLoged;
            }
        } 

        $scope.logOut = function(){
            $localStorage.$reset({
                counter: 42
            });
            var newpath = window.location.origin;
            console.log(newpath);
            window.location = newpath;            
        }       

		$scope.openTab = function(tabName) {      
            if(tabName == 'HOME')
            {
            var newpath = window.location.origin;
            newpath = newpath+"/home";
            console.log(newpath);
            window.location = newpath;
            }                                                                                                               	
		}             
        getUserLogin();
    }
]);