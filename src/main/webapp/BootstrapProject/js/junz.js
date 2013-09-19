$(document).ready(function(){
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

  var preFocusTr;
  $("#userTbl tbody tr").live('click', function(event){
    if(preFocusTr)preFocusTr.css('background-color', 'white');
    preFocusTr = $(this);
    $(this).css('background-color', 'yellow');
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
        /*tr.append('<td data-value=' + item.name + ' >' + item.name + '</td>');*/
	    	$('<td>').html(item.name).appendTo(tr);
	    	$('<td>').html(item.errorDatetime).appendTo(tr);
	    	$('<td>').html(item.sourceDbName).appendTo(tr);
        $('<td>').html(item.targetDbName).appendTo(tr);
	      tbody.append(tr);
        $.bootstrapSortable(true);
	    });
  });	
}