$(document).ready(
		function() {

			// GET REQUEST
			$("#getStudentById").click(function(event) {

				validateCheckbox();
				validateIdInput();

				var id = $('#searchId').val();

				var result = transformDBsToUrl();

				ajaxGetById(id, result);
			});

			function ajaxGetById(id, result) {

				$.ajax({
					type : "GET",
					url : window.location + "api/student/" + id + "?" + result,
					success : function(result) {
						if (result != null) {
							$('#resultDivMongo ul').empty();
							$('#resultDivMYSQL ul').empty();
							result.dbResponses.forEach(function(response) {
								switch (response.state) {
								case "SUCCESS":
									printStudent(response);
									break;
								case "CONNECTION_PROBLEM":
									printConnectionProblem(response);
									break;
								case "NO_SUCH_ID":
									printNoSuchId(response);
									break;
								default:
									console.log("Problem")
								}
							});
						}
					},
					error : function(e) {
						$('#resultDivAll ul').empty();
						$('#resultDivAll .list-result').append(
								"<strong>System problem</strong>");
						console.log("ERROR: ", e);
					}
				});
			}

			function transformDBsToUrl(dbTypes) {

				var dbTypes = [];

				$(".dbType:checked").each(function() {
					dbTypes.push("dbTypes=" + $(this).val() + "&");
				});

				dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
						.slice(0, -1);

				var result = dbTypes.join("");

				return result;
			}

			function validateCheckbox() {

				checked = $(".dbType:checked").length;

				if (!checked) {
					alert("You must check at least one checkbox.");
					return false;
				}
			}

			function validateIdInput() {

				if ($('#searchId').val() == null || $('#searchId').val() == ""
						|| $('#searchId').val().length == 0) {
					alert("Please insert ID");
					return false;
				}
			}

			function printStudent(response) {

				var result = "Student with Id = "
						+ response.students.students[0].id + ", firstname = "
						+ response.students.students[0].name + ", age = "
						+ response.students.students[0].age + ", grade = "
						+ response.students.students[0].grade + "<br>";

				print(response.dbType, result);
			}

			function printConnectionProblem(response) {
				var result = "Connection problem with the Database";
				print(response.dbType, result);
			}

			function printNoSuchId(response) {
				var result = "No such ID";
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

		})