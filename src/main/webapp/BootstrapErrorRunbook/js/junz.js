$(document).ready(function(){

  var map;
  var page;
  var pageSize = 5;
  
 /* $('#requestNameLb').prop("readonly",true);*/

 /* $("#preBtn").click(function(){
    var tbl = $("#userTbl");
    page--;
    if(page < 0)page = 0;
    loadTable(tbl, map, page, pageSize);
  });

  $("#nextBtn").click(function(){
    var tbl = $("#userTbl");
    
    if(page * pageSize + pageSize < Object.keys(map).length - 1){
      loadTable(tbl, map, ++page, pageSize);
    }
  });

  $("#logonBtn").click(function(){
	  var tbl = $("#userTbl");
    var startDate = $('#startDateTxt').val();
    var endDate = $('#endDateTxt').val();

    var url = '../alcazar/failure/get/startDate/{startDate}/endDate/{endDate}';
    url = url.replace('{startDate}', startDate);
    url = url.replace('{endDate}', endDate);

    map = {};
    page = 0;
	  loadFailureTable(url, tbl, map, pageSize);
  });

  $('#startDatePicker').datepicker().on('changeDate', function(ev){
  	$(this).datepicker('hide');  
  });

  $('#endDatePicker').datepicker().on('changeDate', function(ev){
  	$(this).datepicker('hide');  
  });*/

  var preFocusTr;
  $("#userTbl tbody tr").live('click', function(event){
    if(preFocusTr)preFocusTr.css('background-color', 'white');
    preFocusTr = $(this);
    $(this).css('background-color', 'yellow');
    var cells = preFocusTr.find("td");
    $("#errId").val(cells[0].innerHTML);
    $("#errName").val(cells[1].innerHTML);
    $("#TextBoxesGroup").find("div").remove();
    //alert(cells[0].innerHTML);
    //alert(map[$(this).find("td").first().text()].name);
  });

  var counter = 1;
  $("#updateBtn").click(function (e) {
   e.preventDefault();
    if(counter>10){
              alert("Only 10 textboxes allow");
              return false;
    }  

    var jsonItem = [];
    appendFailureCondition(jsonItem, $("#TextBoxesGroup"), counter++);

  });
  
});

function appendFailureCondition(jsonItem, div, num){
  var newTextBoxDiv = $(document.createElement('div'))
       .attr("class", 'row');
 
  var select = '<select class="selectpicker" data-style="btn-warning"><option title="Combo 1">STRING_MATCH</option><option title="Combo 2">STRING_PATTERN</option></select>';

  newTextBoxDiv.html('<label>Condition #'+ num + ' : </label>' + select +
        '<textarea type="text" name="textbox' + num + 
        '" id="textbox' + num + '" value="" >');
 
  newTextBoxDiv.appendTo(div);
}

function loadFailureTable(url, tbl, map, pageSize){
  $.getJSON(url, {/*somedata*/}, function(json_data){
	    //no need for parsejson
	    //use the json_data object
	    var tbody = tbl.find('tbody');
	    tbody = tbl.find('tbody');
	    tbl.find('td').remove();
	    $.each(json_data, function(index, item){
        map[item.id] = item;    
/*	    	var tr = $('<tr>');
        $('<td>').html(item.id).appendTo(tr);
	    	$('<td>').html(item.name).appendTo(tr);
	    	$('<td>').html(item.errorDatetime).appendTo(tr);
	    	$('<td>').html(item.sourceDbName).appendTo(tr);
        $('<td>').html(item.targetDbName).appendTo(tr);
	      tbody.append(tr);
        $.bootstrapSortable(true);*/
	    });

      loadTable(tbl, map, 0, pageSize);
  });	
}

function loadTable(tbl, map, page, pageSize){
  var tbody = tbl.find('tbody');
  tbody = tbl.find('tbody');
  tbl.find('td').remove();

  var keys = Object.keys(map);
  for (var i = 0 + page * pageSize; i < page * pageSize + pageSize; i++) {
    var item = map[keys[i]];
    var tr = $('<tr>');
    /*tr.append('<td data-value=' + item.name + ' >' + item.name + '</td>');*/
    $('<td>').html(item.id).appendTo(tr);
    $('<td>').html(item.name).appendTo(tr);
    $('<td>').html(item.errorDatetime).appendTo(tr);
    $('<td>').html(item.sourceDbName).appendTo(tr);
    $('<td>').html(item.targetDbName).appendTo(tr);
    $('<td>').html(item.step).appendTo(tr);
    tbody.append(tr);
    $.bootstrapSortable(true);
    //Do something
  }
}