$(document).ready(
		function() {

			// GET REQUEST
			$("#getStudentById").click(function(event) {
				var id = $('#searchId').val();
				var dbType = $('#dbType').val();
				event.preventDefault();
				ajaxGetById(id, dbType);
			});

			function ajaxGetById(id, dbType) {

				$.ajax({
					type : "GET",
					url : window.location + "api/student/" + dbType + "/" + id,
					success : function(result) {
						if (result != null) {
							$('#resultDiv ul').empty();
							var custList = "";
							var student = "- Student with Id = " + result.id
									+ ", firstname = " + result.name
									+ ", age = " + result.age + ", grade = "
									+ result.grade + "<br>";
							$('#resultDiv .list-result').append(student)
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