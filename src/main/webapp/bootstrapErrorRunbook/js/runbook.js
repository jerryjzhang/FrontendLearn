$(document).ready(
		function() {

			var map = {};
			var page;
			var pageSize = 5;

			$("#pleaseWaitDialog").modal('show');
			// loadFailureTable('../alcazar/runbook/get/all/fake',
			// $("#userTbl"), map);
			loadFailureTable('mockJsonData.json', $("#userTbl"), map);

			/*
			 * $("#preBtn").click(function(){ var tbl = $("#userTbl"); page--;
			 * if(page < 0)page = 0; loadTable(tbl, map, page, pageSize); });
			 * 
			 * $("#nextBtn").click(function(){ var tbl = $("#userTbl");
			 * 
			 * if(page * pageSize + pageSize < Object.keys(map).length - 1){
			 * loadTable(tbl, map, ++page, pageSize); } });
			 */

			var preFocusTr;
			var counter = 1;
			$("#userTbl tbody tr").live(
					'click',
					function(event) {
						if (preFocusTr)
							preFocusTr.css('background-color', 'white');
						preFocusTr = $(this);
						$(this).css('background-color', 'yellow');
						var cells = preFocusTr.find("td");

						var id = cells[0].innerHTML;
						var jsonItem = map[id];

						$("#errId").val(id);
						$("#errName").val(jsonItem.name);
						$("#errSuggestion").val(jsonItem.suggestion);
						$("#errType").val(jsonItem.type);

						$("#conditionGroup").find("div").remove();
						for ( var i = 0; i < jsonItem.conditions.length; i++) {
							appendFailureCondition(jsonItem.conditions[i],
									$("#conditionGroup"), i + 1);
						}

						counter = jsonItem.conditions.length + 1;
					});

			$("#addBtn").click(
					function(e) {
						e.preventDefault();
						if (counter > 10) {
							alert("Only 10 textboxes allow");
							return false;
						}

						var jsonItem = [];
						appendFailureCondition(jsonItem, $("#conditionGroup"),
								counter++);
					});

			$("#resetBtn").click(function(e) {
				e.preventDefault();
				counter = 1;
				$("#errId").val("");
				$("#errName").val("");
				$("#errSuggestion").val("");
				$("#conditionGroup").find("div").remove();
			});

			$("#updateBtn").click(function(e) {
				e.preventDefault();
				var id = $('#errId').val();
				
				var jsonItem = map[id];
				if(id == ""){jsonItem = new Object();}
				
				jsonItem.name = $("#errName").val();
				jsonItem.suggestion = $("#errSuggestion").val();
				jsonItem.type = $("#errType").val();

				var condTypes = $('#conditionGroup').find('select');
				var condValues = $('#conditionGroup').find('textarea');

				for ( var i = 0; i < condTypes.length; i++) {
					jsonItem.conditions[i].type = condTypes[i].value;
					jsonItem.conditions[i].value = condValues[i].value;
				}

				// TODO: post to the backend and refresh table
				postErrors('../alcazar/runbook/post/error', jsonItem);

				alert("Update successfully " + jsonItem.name);
			});

			$("#deleteBtn").click(function(e) {
				e.preventDefault();
				alert("Delete successfully");
			});

		});

function appendFailureCondition(jsonItem, div, num) {
	var newTextBoxDiv = $(document.createElement('div'));

	var condTypeId = "condType" + num;
	var condId = "cond" + num;
	var select = '<select id='
			+ condTypeId
			+ ' data-style="btn-warning"><option title="Combo 1">STRING_MATCH</option><option title="Combo 2">STRING_PATTERN</option><option title="Combo 3">STEP</option></select>';

	newTextBoxDiv.html('<label class="col-lg-2 control-label">Condition #'
			+ num + ' : </label><div class="col-lg-10">' + select
			+ '<textarea class="form-control" rows="2"' + '" id=' + condId
			+ '></div>');

	newTextBoxDiv.appendTo(div);

	$("#" + condTypeId).val(jsonItem.type);
	$("#" + condId).val(jsonItem.value);
}

function postErrors(url, json) {
	$.ajax({
		url : url,
		type : 'POST',
		data : JSON.stringify(json),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		async : false,
		success : function(msg) {
			alert(msg);
		}
	});
}

function loadFailureTable(url, tbl, map) {
	$.getJSON(url, {/* somedata */}, function(json_data) {
		// no need for parsejson
		// use the json_data object
		refreshTable(json_data, tbl, map);
		$("#pleaseWaitDialog").modal('hide');
	});
}

function refreshTable(json_data, tbl, map) {
	var tbody = tbl.find('tbody');
	tbody = tbl.find('tbody');
	tbl.find('td').remove();
	$.each(json_data, function(index, item) {
		map[item.id] = item;
		insertTableRow(tbl, item);
	});
	$("#pleaseWaitDialog").modal('hide');
}

function insertTableRow(tbl, item) {
	var tbody = tbl.find('tbody');
	tbody = tbl.find('tbody');
	/* tbl.find('td').remove(); */

	var tr = $('<tr>');
	/* tr.append('<td data-value=' + item.name + ' >' + item.name + '</td>'); */
	$('<td>').html(item.id).appendTo(tr);
	$('<td>').html(item.name).appendTo(tr);
	tbody.append(tr);
	$.bootstrapSortable(true);
}