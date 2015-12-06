/**
 * 
 */
var app = angular.module('rh', ['ngRoute','angular-growl']);

/**
 * configuration du module principal
 */

app.config(['$routeProvider',
            function($routeProvider){
			//systeme de routage
				$routeProvider
				.when('/allCollabo',{
					templateUrl:'partials/liste.html',
					controller: 'rhController'
				})
				.when('/addCollabo',{
					templateUrl:'partials/ajout.html',
					controller: 'rhController'
				})
				.when('/Collaborateurs/:ref', {
					templateUrl: 'partials/details_collaborateur.html',
					controller:'rhController'
				})
				.otherwise({
					redirectTo:'/allCollabo'
				});
}
]);



/**
 * Contrôleur de l'application app
 */
app.controller('rhController', function($scope, $http, $routeParams, growl, $location){
    $scope.user={};
	$scope.collaborateurs=[];
	$scope.collaborateur={};
	$scope.idCollaborateur=0;;
	//$scope.motCle=null;
	$scope.url = 'http://localhost:1111/collaborateurs/';
	$scope.pageCourante=0;
	
	$scope.lister=function(){
		$http.get("http://localhost:1111/collaborateurs?page="+$scope.pageCourante)
		.success(function(data){
			$scope.collaborateurs=data;
			$scope.pages=new Array(data.totalPages);
		});
	};
	$scope.gotoPage=function(p){
		$scope.pageCourante=p;
		$scope.lister()
	};
	
	
	$scope.ajouterCollaborateur = function () {
		var utilisateur={"nom":$scope.user.nom , "prenom": $scope.user.prenom ,"dateEmbauche":$scope.user.dateEmbauche , "email": $scope.user.email }
		$http.post("http://localhost:1111/collaborateurs", utilisateur)
		.success(function(response){
		        growl.success('Collaborateur ajouté avec succes.',{title: 'Success!'});
			console.log ("collaborateur ajouté");
		});

	    };
	    
	$scope.voirDetailsCollaborateur= function(){
		var id= $routeParams.ref;
		$http.get($scope.url.concat(id))
		.success(function(data){
			console.log(data);
			$scope.collaborateur=data;
			
		});
	};
	
	$scope.supprimerCollaborateur= function(refDel){
		$http.delete($scope.url.concat(refDel))
		.success(function(data){
			growl.success('Collaborateur supprimé avec succes.',{title: ''});
			$scope.lister();
		});
	}
	
	  
	
})