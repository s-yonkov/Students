$(document).ready(
		function() {

			// GET REQUEST
			$("#getAllStudentsBtnId").click(function(event) {
				
			      checked = $(".dbType:checked").length;

			      if(!checked) {
			        alert("You must check at least one checkbox.");
			        return false;
			      }
			      
				event.preventDefault();
				var dbTypes = [];
				
				$(".dbType:checked").each(function() {
					dbTypes.push("dbTypes=" + $(this).val() + "&");
				});
				
				dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1].slice(0, -1);
				console.log(dbTypes);
				var result = dbTypes.join("");
				console.log(result);
				ajaxGet(result);
			});

			// DO GET
			function ajaxGet(result) {
				$.ajax({
					type : "GET",
					url : window.location + "api/student/all?" + result,
					success : function(result) {
						if (result != null) {
							$('#resultDiv ul').empty();							
							var custList = "";
							$.each(result, function(i, student) {
								var student = "- Student with Id = "
										+ student.id + ", firstname = "
										+ student.name + ", age = "
										+ student.age + ", grade = "
										+ student.grade + "<br>";
								$('#resultDiv .list-result').append(student);
							});
							console.log("Success: ", result);
						} else {
							$('#resultDiv ul').empty();	
							$('#resultDiv .list-result').append("<strong>No available records to show</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$('#resultDiv ul').empty();	
						$('#resultDiv .list-result').append("<strong>Connection problem</strong>");
						console.log("ERROR: ", e);
					}
				});
			}

		})