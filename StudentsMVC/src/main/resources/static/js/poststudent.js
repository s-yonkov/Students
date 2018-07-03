$(document).ready(function() {
	
	
	$("#studentForm").submit(function(event) {
		
		validateCheckbox();      
	      
		ajaxPost();	
		
		event.preventDefault();
	});

	function ajaxPost() {
		
		// PREPARE INPUT DATA
		var dbTypes = [];
		$(".dbType:checked").each(function() {
			dbTypes.push($(this).val());
		});
		
		var rawStudent = new Object();
		rawStudent.id = $("#id").val();
		rawStudent.name = $("#name").val(),
		rawStudent.age = $("#age").val(),
		rawStudent.grade = $("#grade").val()
		
		var input = new Object();
		input.dbTypes = dbTypes;
		input.student = rawStudent;
		
		// DO POST
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/student/save",
			data : JSON.stringify(input),
			dataType : 'json',
			success : function(result) {
				if (result != null) {
					$('#resultDivMongo ul').empty();
					$('#resultDivMYSQL ul').empty();
					$('#resultDivAll ul').empty();
					result.dbResponses.forEach(function(response) {
						switch (response.state) {
						case "SUCCESS":
							printStudent(response);
							break;
						case "CONNECTION_PROBLEM":
							printConnectionProblem(response);
							break;
						default:
							console.log("Problem in States swtich-case")
						}
					});
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});

		// Reset FormData after Posting
		resetData();
	}

	function resetData() {
		$("#id").val("");
		$("#name").val("");
		$("#age").val("");
		$("#grade").val("");
	}
		
	function validateCheckbox() {

		checked = $(".dbType:checked").length;

		if (!checked) {
			alert("You must check at least one checkbox.");
			return false;
		}
	}
	
	function printStudent(response) {

		var result = "Student with Id = "
				+ response.students.students[0].id + ", firstname = "
				+ response.students.students[0].name + ", age = "
				+ response.students.students[0].age + ", grade = "
				+ response.students.students[0].grade + " - Has been added" + "<br/>";

		print(response.dbType, result);
	}
	
	function print(type, result) {
		switch (type) {
		case "MONGO":
			$('#resultDivMongo ul').empty();
			$('#resultDivMongo .list-result').append(result);
			break;
		case "MYSQL":
			$('#resultDivMYSQL ul').empty();
			$('#resultDivMYSQL .list-result').append(result);
			break;
		default:
			console.log("Error in print")
		}
	}
	
	function printConnectionProblem(response) {
		var result = "Connection problem with the Database - unable to add the student.";
		$('#resultDivMYSQL ul').empty();
		$('#resultDivMongo ul').empty();
		$('#resultDivAll ul').empty();
		$('#resultDivAll .list-result').append(result);
	}
})