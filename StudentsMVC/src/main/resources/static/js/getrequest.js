$(document).ready(
		function() {

			// GET REQUEST
			$("#getAllStudentsBtnId").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : window.location + "api/student/all",
					success : function(result) {
						if (result != null) {
							$('#resultDiv ul').empty();
							var custList = "";
							$.each(result, function(i, student) {
								var student = "- Student with Id = " + student.id
										+ ", firstname = " + student.name
										+ ", age = " + student.age
										+ ", grade = " + student.grade
										+ "<br>";
								$('#resultDiv .list-result').append(student)
							});
							console.log("Success: ", result);
						} else {
							$("#resultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#postResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}			
						
		})