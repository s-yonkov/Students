$(document).ready(
		function() {

			// GET REQUEST
			$("#getAllStudentsBtnId").click(function(event) {

				validateCheckbox();

				var result = transformDBsToUrl();

				ajaxGetAll(result);
			});

			function ajaxGetAll(result) {

				$.ajax({
					type : "GET",
					url : window.location + "api/student/all?" + result,
					success : function(result) {
						if (result != null) {
							$('#resultDivMongo ul').empty();
							$('#resultDivMYSQL ul').empty();
							result.dbResponses.forEach(function(response) {
								switch (response.state) {
								case "SUCCESS":
									printStudents(response);
									break;
								case "CONNECTION_PROBLEM":
									printConnectionProblem(response);
									break;
								case "INVALID_DB":
									printInvalidDB();
								    break;
								default:
									console.log("Problem in States swtich-case")
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

			function printStudents(response) {
				var result = "";
				response.students.students.forEach(function(student){
				result = result.concat("Student with Id = "
						+ student.id + ", firstname = "
						+ student.name + ", age = "
						+ student.age + ", grade = "
						+ student.grade + "<br/>");
				});

				print(response.dbType, result);
			}

			function printConnectionProblem(response) {
				var result = "Connection problem with the Database";
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
			
			function printInvalidDB() {
				$('#resultDivMYSQL ul').empty();
				$('#resultDivMongo ul').empty();
				$('#resultDivAll ul').empty();
				$('#resultDivMYSQL .list-result').append("Invalid Database type selected");
			}

		})