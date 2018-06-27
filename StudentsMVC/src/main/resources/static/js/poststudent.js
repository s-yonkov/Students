$(document).ready(function() {

	$("#studentForm").submit(function(event) {
		event.preventDefault();
		ajaxPost();
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
			url : window.location + "api/student/save",
			data : JSON.stringify(input),
			dataType : 'json',
			success : function(result) {
				if (result.massage == "Done") {
					$('#resultDiv ul').empty();
					var student = "- Student with Id = "
						+ result.student.id + ", firstname = "
						+ result.student.name + ", age = "
						+ result.student.age + ", grade = "
						+ result.student.grade + " - Has been added" + "<br>";
					$('#resultDiv .list-result').append(student);
				} else {
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
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
})