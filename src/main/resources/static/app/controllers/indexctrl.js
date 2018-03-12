'use strict';

angular.module('WebCrudTest')
.controller("indexCtrl", ['$scope', '$http', '$location', '$localStorage', 'UserService',
    function($scope, $http, $location, $localStorage, UserService){
    	$scope.titleArea = 'Bienvenido (CRUD Simple)';
        $scope.userLoged = 'Inicia Sesion';
        $scope.showtabs = false;
        $scope.logInButton = 'Registrar';
        $scope.loginReg = 'Registrar Usuario';
        $scope.flagLogin = true;

        function validSession(){
            if($localStorage.usrLoged == undefined || $localStorage.usrLoged == "")
            {        
                console.log('not logged');           
            }
            else
            {
                console.log('is logged');
                var newpath = window.location.origin;
                newpath = newpath+"/home";
                window.location = newpath;                  
            }
        }

        $scope.onlogin = function(){
            var name = document.getElementById("name").value;
            var pass = document.getElementById("pass").value;
            
            if($scope.logInButton == 'Entrar')
            {
                if($scope.userMail == "")
                {
                    return alert('Campo mail vacío');
                }
                if(pass == "")
                {
                    return alert('Campo contraseña vacío');
                }                                 
                var sendObj = {};
                sendObj.mail = $scope.userMail;
                sendObj.pass = document.getElementById("pass").value;
                UserService.loadUser(sendObj).then(
                    function successCallback(response){                
                    if(response.username == "" || response.username == undefined)
                    {
                        alert('Usuario o contraseña no validos.')
                    }
                    else
                    {
                        var data = response;
                        $localStorage.usrLoged = response.username;
                        $scope.userLoged = $localStorage.usrLoged;
                        var newpath = window.location.origin;
                        newpath = newpath+"/home";               
                        console.log('success' );
                        window.location = newpath;
                    }                    
                 },function errorCallback(response){
                    console.log('call error')
                });  
            }
            else if($scope.logInButton == 'Registrar')
            {
                if($scope.userMail == "")
                {
                    return alert('Campo mail vacío');
                }
                if(name == "")
                {
                    return alert('Campo nombre vacío');
                }
                if(pass == "")
                {
                    return alert('Campo contraseña vacío');
                }               
                var usrObj = {};
                usrObj.mail = $scope.userMail;
                usrObj.name = document.getElementById("name").value;
                usrObj.pass = document.getElementById("pass").value;
                UserService.createUser(usrObj).then(function successCallback(response){                                             
                    console.log('success');
                    if(response.username == "" || response.username == null || response.username == undefined)
                    {
                        return alert('Ya existe una cuenta registrada con ese mail.');
                    }
                    else
                    {
                        alert('Usuario creado correctamente!');
                        document.getElementById("mail").value = '';
                        document.getElementById("name").value = '';
                        document.getElementById("pass").value = ''; 
                        $scope.loginReg = 'Iniciar Sesion';
                        $scope.logInButton = 'Entrar';
                        $scope.flagLogin = false; 
                    }
                 },function errorCallback(response){
                    console.log('call error')
                });                 
            }
        }

        $scope.logInFn = function(){
            if($scope.userLoged == 'Inicia Sesion')
            {
                $scope.userLoged = 'Registrarse';
                $scope.loginReg = 'Iniciar Sesion';
                $scope.logInButton = 'Entrar';
                $scope.flagLogin = false;
            }
            else
            {
                $scope.userLoged = 'Inicia Sesion';
                $scope.loginReg = 'Registrar Usuario';
                $scope.logInButton = 'Registrar';
                $scope.flagLogin = true;
            }
        }
           
        validSession();
    }
]);