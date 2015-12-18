/**
 * Déclaration du module 
 */
var app2 = angular.module('rhProjets', []);


app2.config(['$routeProvider',
            function($routeProvider){
			//systeme de routage
				$routeProvider
				.when('/projets',{
					templateUrl:'partials/liste_projets.html',
					controller: 'projetController'
				})
				.when('/addProjet',{
					templateUrl:'partials/ajout_projet.html',
					controller: 'projetController'
				})
				.when('/projets/:ref', {
					templateUrl: 'partials/details_projet.html',
					controller:'projetController'
				})
				
}
]);
/**
 * Contrôleur de l'application app2
 */
app2.controller('projetController', function($scope, $http,$routeParams, growl, $location){
	$scope.projets=[];
	$scope.projet={};
	$scope.pageCourante=0;
	$scope.url = 'http://localhost:1111/projets/';
	
	$scope.listerProjets=function(){
		$http.get("/projets?page="+$scope.pageCourante)
		.success(function(data){
			$scope.projets=data;
			/*$scope.pages=new Array(data.totalPages);*/
		});
	};
	
	$scope.ajouterProjet = function () {
		var Projet={"intitule":$scope.projet.intitule , "dateDebut": $scope.projet.dateDebut ,"dateFin":$scope.projet.dateFin }
		$http.post("http://localhost:1111/projets", Projet)
		.success(function(response){
			//$scope.produits.push("da");
		        growl.success('Projet ajouté avec succes.');
			console.log ("projet ajouté");
		});

	    };
	    
	    $scope.supprimerProjet= function(refDel){
			$http.delete($scope.url.concat(refDel))
			.success(function(data){
				growl.success('Projet supprimé avec succes.',{title: ''});
				$scope.lister();
			});
		}
	    
	
	
	
	
		
	
	
});



