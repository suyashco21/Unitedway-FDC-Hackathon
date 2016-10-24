if ($(window).width() <= 350){	
		// do something here
		
		('#tag2').attr("placeholder","");
	}
var zipi;
$( function() {
    var availableTags = [
      "Fulton",
      "Cobb",
      "Decatur",
      "Conyers",
      "Butts",
      "Cherokee",
      "Clayton",
      "Coweta",
      "Dekalb",
      "Douglas",
      "Fayette",
      "Gwinett",
      "Henry",
      "Paulding",
      "Rockdale",
	  "30205", 
	  "30214", 
	  "30215", 
	  "30269", 
	  "30290", 
	  "30305" ,
	  "30328" 
    ];
    $( "#tag1" ).autocomplete({
      source: function(request, response) {
        var results = $.ui.autocomplete.filter(availableTags, request.term);
        
        response(results.slice(0, 3));
    } 
	  //availableTags
    });
	  $('#tag1').keyup( function(e) {
		  if(e.keyCode == 13)
    {
		if($("#tag1").val() == "Fulton")
		{
        changeValue(5);
		}
		else if ($("#tag1").val() == "Fayette") 
		{
		  changeValue(7);	
		}
		else if ($("#tag1").val() == "Cobb") 
		{
		  changeValue(8);	
		}
		else if ($("#tag1").val() == "Butts") 
		{
		  changeValue(9);	
		}
		else if ($("#tag1").val() == "Cherokee") 
		{
		  changeValue(10);	
		}
		else if ($("#tag1").val() == "Clayton") 
		{
		  changeValue(11);	
		}
		else if ($("#tag1").val() == "Rockdale") 
		{
		  changeValue(12);	
		}
		else if ($("#tag1").val() == "Coweta") 
		{
		  changeValue(13);	
		}
		else if ($("#tag1").val() == "Douglas") 
		{
		  changeValue(14);	
		}
		else if ($("#tag1").val() == "Henry") 
		{
		  changeValue(15);	
		}
		else if ($("#tag1").val() == "Paulding") 
		{
		  changeValue(16);	
		}
		else if ($("#tag1").val() == "30205" || $("#tag1").val() == "30214" || $("#tag1").val() == "30215" || $("#tag1").val() == "30269" || $("#tag1").val() == "30290")
		{
			zipi = $("#tag1").val();
			changeValue(17);
		}
		else if ($("#tag1").val() == "30305" || $("#tag1").val() == "30328" )
		{
			zipi = $("#tag1").val();
			changeValue(18);
		}
		
		
		
    }
		  
          
    });
  } ); 
  $( function() {
    var availableTags = [
      "Fulton",
      "Cobb",
      "Decatur",
      "Conyers",
      "Butts",
      "Cherokee",
      "Clayton",
      "Coweta",
      "Dekalb",
      "Douglas",
      "Fayette",
      "Gwinett",
      "Henry",
      "Paulding",
      "Rockdale",
	  "30205", 
	  "30214", 
	  "30215", 
	  "30269", 
	  "30290", 
	  "30305" ,
	  "30328" 
    ];
    $( "#tag2" ).autocomplete({
      source: function(request, response) {
        var results = $.ui.autocomplete.filter(availableTags, request.term);
        
        response(results.slice(0, 3));
    } 
	  //availableTags
    });
	  $('#tag2').keyup( function(e) {
		  if(e.keyCode == 13)
    {
		if($("#tag2").val() == "Fulton")
		{
        changeValue(5);
		}
		else if ($("#tag2").val() == "Fayette") 
		{
		  changeValue(7);	
		}
		else if ($("#tag2").val() == "Cobb") 
		{
		  changeValue(8);	
		}
		else if ($("#tag2").val() == "Butts") 
		{
		  changeValue(9);	
		}
		else if ($("#tag2").val() == "Cherokee") 
		{
		  changeValue(10);	
		}
		else if ($("#tag2").val() == "Clayton") 
		{
		  changeValue(11);	
		}
		else if ($("#tag2").val() == "Rockdale") 
		{
		  changeValue(12);	
		}
		else if ($("#tag2").val() == "Coweta") 
		{
		  changeValue(13);	
		}
		else if ($("#tag2").val() == "Douglas") 
		{
		  changeValue(14);	
		}
		else if ($("#tag2").val() == "Henry") 
		{
		  changeValue(15);	
		}
		else if ($("#tag2").val() == "Paulding") 
		{
		  changeValue(16);	
		}
		else if ($("#tag2").val() == "30205" || $("#tag2").val() == "30214" || $("#tag2").val() == "30215" || $("#tag2").val() == "30269" || $("#tag2").val() == "30290")
		{
			zipi = $("#tag2").val();
			changeValue(17);
		}
		else if ($("#tag2").val() == "30305" || $("#tag2").val() == "30328" )
		{
			zipi = $("#tag2").val();
			changeValue(18);
		}
		
		
		
    }
		  
          
    });
  } );
$(document).ready(function(){
//google.maps.event.addDomListener(window, 'load', initialize);

var latUrl;
			latUrl= "https://heat-map.cfapps.io/heatmap/georgia/atlanta";
			//latUrl= "https://heat-map.apps.us-oma1-inp1.1dc.com/heatmap/georgia/atlanta";
		 $.ajax({
       url: latUrl,
        type: 'GET',
        success: function(data) {   
		var total = (data.cityCommunityMeasure +   data.cityFamilyMeasure + data.cityChildMeasure)/3;
		$("#button1").text('Child\nScore '+Math.round(data.cityChildMeasure));
		$("#button2").text('Family\nScore '+Math.round(data.cityFamilyMeasure));
		$("#button3").text('Community\nScore '+Math.round(data.cityCommunityMeasure));  
		$("#button4").text('Overall\nScore '+Math.round(total));  
		$("#button5").text('Overall\nScore: '+Math.round(total)); 
		/*var legend = document.getElementById('legend');
      
          var div = document.createElement('div');
          div.innerHTML = "<img src=\" legend.png \">";
          legend.appendChild(div);*/
		initialize(data,4); 
        }
	});
});
var map;
function initialize(data,iden) {
	if(iden == 5)
	{
		map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 9,
          center: {lat: 33.78, lng: -84.40},
		  minZoom : 8,
		  mapTypeControl: false,
          mapTypeId: 'roadmap'
        });
	}
	else if (iden==17 || iden ==18)
	{
		map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 11,
          center: {lat: 33.78, lng: -84.40},
		  minZoom : 8,
		  mapTypeControl: false,
          mapTypeId: 'roadmap'
        });
	}
	else
	{
	map = new google.maps.Map(document.getElementById('map-canvas'), {
          zoom: 8,
          center: {lat: 33.78, lng: -84.40},
		  minZoom : 8,
		  mapTypeControl: false,
          mapTypeId: 'roadmap'
        });
	}

		var colorChecker ;
	  var coord = [];
	  var temp;
	  var bermuda = new Array();
	  var zipColor;
	  var singleZipName;
	  var singleZipChild;
	  var singleZipCommunity;
	  var singleZipFamily;
	  var singleZipTotal;
	  total = [];
	  //console.log(data);
	  for(b in data.countyList)
	  {
		 if(iden == 17 || iden ==18)
		 {
			 for (k in data.countyList[b].zipCodeList)
			 {
				 if(data.countyList[b].zipCodeList[k].zipCode == zipi)
				 {
					 for (m in data.countyList[b].zipCodeList[k].coordinates)
					 {
					 coord.push(new google.maps.LatLng(data.countyList[b].zipCodeList[k].coordinates[m][1],data.countyList[0].zipCodeList[k].coordinates[m][0]));
					 colorChecker = (data.countyList[b].zipCodeList[k].countyChildMeasure + data.countyList[b].zipCodeList[k].countyFamilyMeasure + data.countyList[b].zipCodeList[k].countyCommunityMeasure)/3;
					 singleZipName = data.countyList[b].zipCodeList[k].zipCode;
					 singleZipTotal = colorChecker;
					 singleZipChild = data.countyList[b].zipCodeList[k].countyChildMeasure ;
					 singleZipCommunity = data.countyList[b].zipCodeList[k].countyCommunityMeasure ;
					 singleZipFamily = data.countyList[b].zipCodeList[k].countyFamilyMeasure;
					 }
			     }
			 }
		 }
		 else
		 { 
		for(a=0;a<data.countyList[b].coordinates.length;a++)  {
	  	//for (a in data.countyList[b].coordinates) {
			coord.push(new google.maps.LatLng(data.countyList[b].coordinates[a][1],data.countyList[b].coordinates[a][0]));	
		}
		}
		if(iden == 1)
		{
			colorChecker = data.countyList[b].countyChildMeasure;
		}
		else if(iden == 2)
		{
			colorChecker = data.countyList[b].countyCommunityMeasure;
		}
		else if (iden  == 3)
		{
			colorChecker = data.countyList[b].countyFamilyMeasure;
		}
		else if (iden  == 4)
		{
			colorChecker = (data.countyList[b].countyChildMeasure + data.countyList[b].countyFamilyMeasure + data.countyList[b].countyCommunityMeasure)/3;
			total[b]=colorChecker;
		}
		else if (iden > 4 && iden <= 16)
		{
			colorChecker = (data.countyList[b].countyChildMeasure + data.countyList[b].countyFamilyMeasure + data.countyList[b].countyCommunityMeasure)/3;
			total[b]=colorChecker;
		}
		
		//console.log ("colorChecker: " + colorChecker);
		var countyColor;
		if(colorChecker<20)
		{
		 countyColor = "#FF0000";	
		}
		if(colorChecker>=20 && colorChecker<23)
		{
		  	countyColor = "#FF4000";
		}
		else if (colorChecker>=23 && colorChecker<26)
		{
			countyColor = "#FF8000";
		}
		else if (colorChecker>=26 && colorChecker<29)
		{
			countyColor = "#FFC000";
		}
		else if (colorChecker>=29 && colorChecker<32)
		{
			countyColor = "#FFFF00";
		}
		else if (colorChecker>=32 && colorChecker<35)
		{
			countyColor = "#C0FF00";
		}
		else if (colorChecker>=35 && colorChecker<38)
		{
		    countyColor = "#80FF00";	
		} 
		else if (colorChecker>=38 && colorChecker<41)
		{
			countyColor = "#40FF00";
		}
		else if (colorChecker>=41 && colorChecker<44)
		{
			countyColor = "#10FF00";
		}
		//console.log ("color: " + countyColor);
		bermuda[b] = new google.maps.Polygon({
          paths: coord,
          strokeColor: '#000000',
          strokeOpacity: 0.6,
          strokeWeight: 1,
          fillColor: countyColor,
		fillOpacity: 0.35
          
        });
		coord = [];
		//console.log(b);
		zipColor = countyColor;
	  }
	  var colo;
	  if(iden > 4 && iden <= 16) 
	  {
		  coord = [];
		  for (d in data.countyList[0].zipCodeList)
		  {
			  for (e in data.countyList[0].zipCodeList[d].coordinates)
			  {
				 
				  coord.push(new google.maps.LatLng(data.countyList[0].zipCodeList[d].coordinates[e][1],data.countyList[0].zipCodeList[d].coordinates[e][0]));
				 // console.log(data.countyList[0].zipCodeList[d].coordinates[e][1]);
				 // console.log(data.countyList[0].zipCodeList[d].coordinates[e][0]);
			  }
			  
			   if(d == 0)
				  {
					  colo = "#FFC000";
				  }
				  else if (d == 1)
				  {
					  colo = "#C0FF00";
				  }
				  else if (d == 3)
				  {
					  colo = "#10FF00";
				   }
				   else
				   {
					   colo = "#FF0000";
				  }
			  bermuda[bermuda.length+d] = new google.maps.Polygon({
          paths: coord,
          strokeColor: '#000000',
          strokeOpacity: 0.6,
          strokeWeight: 1,
          fillColor: colo,
		fillOpacity: 0.35
          
        });
		//console.log(bermuda.length+d);
		coord = [];
		  }
	   }
		for (c in bermuda)
		{
			bermuda[c].setMap(map);
		}
		var contentString;
		var infoWindow=[];
	
		for (g in data.countyList)
		{
			infoWindow[g]= new google.maps.InfoWindow;
			//console.log ("g value: "+g + " "+iden);
			if(iden == 1)
			{
				contentString = data.countyList[g].countyName + "<br> Child Score: "+Math.round(data.countyList[g].countyChildMeasure) ;
			}
			else if (iden == 2)
			{
				contentString = data.countyList[g].countyName + "<br> Community Score: "+Math.round(data.countyList[g].countyCommunityMeasure) ;
			}
			else if(iden == 3)
			{
				contentString = data.countyList[g].countyName + "<br> Family Score: "+Math.round(data.countyList[g].countyFamilyMeasure) ;
			}
			else if(iden == 4)
			{
				contentString = data.countyList[g].countyName + "<br> Overall Score: "+Math.round(total[g]) ;
			}
			else if (iden >4 && iden <=16)
			{
				contentString = data.countyList[g].countyName + "<br> Overall Score: "+Math.round(total[g]) ;
			}
			else if (iden == 17 || iden ==18)
			{
				contentString =  singleZipName+ "<br> Total Measure: "+Math.round(singleZipTotal) ;
				$("#button1").text('Child\nMeasure '+Math.round(singleZipChild));
		$("#button2").text('Family\nMeasure '+Math.round(singleZipFamily));
		$("#button3").text('Community\nMeasure '+Math.round(singleZipCommunity));  
		$("#button4").text('Total\nMeasure '+Math.round(singleZipTotal)); 
			}
			//console.log(contentString);
			infoWindow[g].setContent(contentString);	
		}
		bermuda[0].addListener('click',function(event) {
			infoWindow[0].setPosition(event.latLng);
			 infoWindow[0].open(map);	
				});
				bermuda[1].addListener('click',function(event) {
			infoWindow[1].setPosition(event.latLng);
			 infoWindow[1].open(map);	
				});
				bermuda[2].addListener('click',function(event) {
			infoWindow[2].setPosition(event.latLng);
			 infoWindow[2].open(map);	
				});
				bermuda[3].addListener('click',function(event) {
			infoWindow[3].setPosition(event.latLng);
			 infoWindow[3].open(map);	
				});
				bermuda[4].addListener('click',function(event) {
			infoWindow[4].setPosition(event.latLng);
			 infoWindow[4].open(map);	
				});
				bermuda[5].addListener('click',function(event) {
			infoWindow[5].setPosition(event.latLng);
			 infoWindow[5].open(map);	
				});
				bermuda[6].addListener('click',function(event) {
			infoWindow[6].setPosition(event.latLng);
			 infoWindow[6].open(map);	
				});
				bermuda[7].addListener('click',function(event) {
			infoWindow[7].setPosition(event.latLng);
			 infoWindow[7].open(map);	
				});
				bermuda[8].addListener('click',function(event) {
			infoWindow[8].setPosition(event.latLng);
			 infoWindow[8].open(map);	
				});
					bermuda[9].addListener('click',function(event) {
			infoWindow[9].setPosition(event.latLng);
			 infoWindow[9].open(map);	
				});
					bermuda[10].addListener('click',function(event) {
			infoWindow[10].setPosition(event.latLng);
			 infoWindow[10].open(map);	
				});
					bermuda[11].addListener('click',function(event) {
			infoWindow[11].setPosition(event.latLng);
			 infoWindow[11].open(map);	
				});
				bermuda[12].addListener('click',function(event) {
			infoWindow[12].setPosition(event.latLng);
			 infoWindow[12].open(map);	
				});
				
}
function changeValue(a)
	{
		var latUrl;
		if(a==5)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Fulton";
		}
		else if(a==7)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Fayette";
		}
		else if(a==8)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Cobb";
		}
		else if(a==9)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Butts";
		}
		else if(a==10)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Cherokee";
		}
		else if(a==11)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Clayton";
		}
		else if(a==12)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Rockdale";
		}
		else if(a==13)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Coweta";
		}
		else if(a==14)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Douglas";
		}
		else if(a==15)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Henry";
		}
		else if(a==16)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Paulding";
		}
		else if(a==17)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Fayette";
		}
		else if(a==18)
		{
			latUrl = "https://heat-map.cfapps.io/heatmap/georgia/atlanta/Fulton";
		}
		else
		{
			latUrl= "https://heat-map.cfapps.io/heatmap/georgia/atlanta";
		}
		 $.ajax({
       url: latUrl,
	  // useDefaultXhrHeader: false,
	   //crossDomain: true,
	   //dataType:"jsonp",
        type: 'GET',
		//headers: {
//                    'Access-Control-Allow-Origin': '*'
//                },
        success: function(data) { 
		if(a > 4 && a<=16)
		{
		var total = (data.countyList[0].countyCommunityMeasure +   data.countyList[0].countyFamilyMeasure +data.countyList[0].countyChildMeasure)/3;
		$("#button1").text('Child\nScore '+Math.round(data.countyList[0].countyChildMeasure));
		$("#button2").text('Family\nScore '+Math.round(data.countyList[0].countyFamilyMeasure));
		$("#button3").text('Community\nScore '+Math.round(data.countyList[0].countyCommunityMeasure));  
		$("#button4").text('Overall\nScore '+Math.round(total)); 
		$("#button5").text('Overall\nScore: '+Math.round(total)); 	
		//$("#wait").css("display", "none"); 
		}
          initialize(data,a);
        }
    });
	}

