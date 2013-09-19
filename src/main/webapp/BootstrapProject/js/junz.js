$(document).ready(function(){
/*  $("#junzBtn").click(function(){
    $("input").val("hehe");
  });

  $(".btn").dblclick(function(){
    $("#junzBtn").fadeToggle();
  });

  $( "#textFirstName" ).change(function() {
  alert($(this).val());
  });*/
  $("#logonBtn").click(function(){
	  var tbl = $("#userTbl");
    var startDate = $('#startDateTxt').val();
    var endDate = $('#endDateTxt').val();

    var url = '../alcazar/failure/get/startDate/{startDate}/endDate/{endDate}';
    url = url.replace('{startDate}', startDate);
    url = url.replace('{endDate}', endDate);

	  loadFailureTable(url, tbl);
  });

  $('#startDatePicker').datepicker().on('changeDate', function(ev){
  	$(this).datepicker('hide');  
  });

  $('#endDatePicker').datepicker().on('changeDate', function(ev){
  	$(this).datepicker('hide');  
  });
});

function loadFailureTable(url, tbl){
  $.getJSON(url, {/*somedata*/}, function(json_data){
	    //no need for parsejson
	    //use the json_data object
	    var tbody = tbl.find('tbody');
	    tbody = tbl.find('tbody');
	    tbl.find('td').remove();
	    $.each(json_data, function(index, item){
	    	var tr = $('<tr>');
	    	$('<td>').html(item.name).appendTo(tr);
	    	$('<td>').html(item.errorDatetime).appendTo(tr);
	    	$('<td>').html(item.sourceDbName).appendTo(tr);
        $('<td>').html(item.targetDbName).appendTo(tr);
	      tbody.append(tr);
	    });
  });	
}