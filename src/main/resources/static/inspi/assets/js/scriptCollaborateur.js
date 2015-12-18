/**
 * 
 */
var app = angular.module('rh', ['ngRoute','angular-growl','ui.bootstrap']);

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
app.controller('rhController', function($scope, $http, $routeParams, growl, $location,$window){
	
    $scope.user={};
	$scope.collaborateurs=[];
	$scope.collaborateur={};
	$scope.idCollaborateur=0;;
	//$scope.motCle=null;
	$scope.identifiants={};
	$scope.url = 'http://localhost:1111/collaborateurs/';
	$scope.pageCourante=0;
	$scope.objectif={};
	$scope.projetsCollavorateur=[];
	$scope.projet = {};
	$scope.projetcollaborateur={};
	$scope.formulairevaluation = false;
	$scope.formulairefeedback = false;
	$scope.feedbackCourant={};
	$scope.objectifCourant={};
	$scope.evaluation={};
	$scope.feedbacks=[];
	$scope.pagequalification = {};
	
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
		var utilisateur={"nom":$scope.user.nom , "prenom": $scope.user.prenom ,"dateNaissance":$scope.user.dateNaissance,"dateEmbauche":$scope.user.dateEmbauche , "poste":$scope.user.poste, "email": $scope.user.email }
		$http.post("http://localhost:1111/collaborateurs", utilisateur)
		.success(function(response){
		        growl.success('Collaborateur ajouté avec succes.',{title: 'Success!'});
			console.log ("collaborateur ajouté");
			$scope.user.nom="";
			$scope.user.prenom="";
			$scope.user.dateEmbauche="";
			$scope.user.email="";
			$location.path("/");
			$scope.$apply();
			growl.success('Collaborateur ajouté avec succes.',{title: 'Success!'});
			
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
	$scope.getProjetsCollaborateur=function(){
		var id = $routeParams.ref
		$http.get("http://localhost:1111/collaborateurs/"+id+"/projets")
		.success(function(data){
			$scope.projetsCollavorateur = data;
		})
	};
	
	$scope.supprimerCollaborateur= function(refDel){
		$http.delete($scope.url.concat(refDel))
		.success(function(data){
			growl.success('Collaborateur supprimé avec succes.',{title: ''});
			$scope.lister();
		});
	};
	
	
	
	//rechercher un projet
	$scope.chercherprojet = function(){
		$http.get("http://localhost:1111/projets/"+$scope.projet.id)
		.success(function(data){
			$scope.projet = data;
			console.log(data);
		})
	};
	
	//ajouter un projet à un collaborateur
	$scope.ajouterprojetcollaborateur=function(){
		var id2= $routeParams.ref;
		$http.post("http://localhost:1111/collaborateurs/"+id2+"/projets?projet="+$scope.projet.id+"&role="+$scope.projetcollaborateur.role+"&jours="+$scope.projetcollaborateur.joursvalorises)
		.success(function(data){
			//$scope.projetsCollavorateur.push([data.projet.intitule,data.projet.dateDebut, data.projet.dateFin, data.rolecollaborateur, data.joursvalorises ]);
			growl.success('projet attribue avec succes.');
			console.log(data)
		})
	};
	//
	
	
	

	
	
	//$scope.objectif.annee= new Date();
	
	$scope.addobjectif = function () {
		var id1= $routeParams.ref;
		var obj ={"categorie": $scope.objectif.categorie ,"intitule":$scope.objectif.nom,"description":$scope.objectif.description ,"annee":$scope.objectif.annee};
		$http.post("http://localhost:1111/collaborateurs/"+id1+"/objectifs", obj)
		.success(function(response){
			$scope.collaborateur.objectifs.push(response);
		        growl.success('Objectif ajouté avec succes.');
		        console.log(obj)
		        console.log(response)
		        
		        
			console.log ("objectif ajouté");
		});

	    };
	    
	    //afficher le formulaire d'evaluation
	    $scope.showform = function(obj) {
	    	$scope.formulairevaluation = true;
	    	$scope.objectifCourant=obj;
	    	console.log($scope.formulairevaluation);
		};
	    
	  //evaluer un objectif
		$scope.evaluerprojet=function(){
			var evaluation = {"mesure": $scope.evaluation.mesure, "responsableMesure":$scope.evaluation.responsable, "poids":$scope.evaluation.poids, "resultat":$scope.evaluation.resultat};
			$http.post("http://localhost:1111/evaluations", evaluation)
			.success(function(data) {
				console.log(data)
				$http.post("http://localhost:1111/objectifs/"+$scope.objectifCourant.id+"/evaluations?evaluation="+data.idEvaluation)
				.success(function(data) {
					
					$scope.formulairevaluation = false;
					growl.success('Objectif evalué avec succes.',{title: ''});
				})
			})
		};
		
		
		//afficher les form de feedback
	    $scope.showdetailsfeedback= function(obj) {
	    	$scope.formulairefeedback = true;
	    	$scope.feedbackCourant=obj;
	    	$http.get("http://localhost:1111/feedbacks/themes?idFeedback="+$scope.feedbackCourant.id+"&page=0&size=10")
	    	.success(function(page) {
	    		$scope.pagequalification = page;
	    	})
	    	//console.log($scope.feedbackCourant);
		};
		
		
		
		//recuperer les feebacks d'un collaborateur
		
		/*$scope.getfeedbacks = function(){
			var id3 = $routeParams.ref;
			$http.get("http://localhost:1111/feedbacks/collaborateurs/"+id3)
			.success(function(data) {
				$scope.feedbacks=data;
				
			})
		};*/
	    
	    
	   
	
	
});










