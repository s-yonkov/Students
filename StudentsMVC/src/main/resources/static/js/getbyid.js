$(document).ready(
		function() {

			// GET REQUEST
			$("#getStudentById").click(function(event) {
				
			      checked = $(".dbType:checked").length;

			      if(!checked) {
			        alert("You must check at least one checkbox.");
			        return false;
			      }
			      
			     if($('#searchId').val() == null || $('#searchId').val() == "" || $('#searchId').val().length == 0){
				        alert("Please insert ID");
				        return false;
			     }
			      
				var id = $('#searchId').val();
				var dbTypes = [];
				
				$(".dbType:checked").each(function() {
					dbTypes.push("dbTypes=" + $(this).val() + "&");
				});
				
				dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1].slice(0, -1);
				console.log(dbTypes);
				
				var result = dbTypes.join("");
				console.log(result);
				
				ajaxGetById(id, result);
			});

			function ajaxGetById(id, result) {

				$.ajax({
					type : "GET",
					url : window.location + "api/student/" + id + "?" + result,
					success : function(result) {
						if (result.name != null) {
							$('#resultDiv ul').empty();
							var custList = "";
							var student = "- Student with Id = " + result.id
									+ ", firstname = " + result.name
									+ ", age = " + result.age + ", grade = "
									+ result.grade + "<br>";
							$('#resultDiv .list-result').append(student);
							console.log("Success: ", result);
						} else {
							$('#resultDiv ul').empty();
							$('#resultDiv .list-result').append("No such ID");
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