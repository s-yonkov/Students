$(document)
		.ready(
				function() {

					// GET REQUEST
					$("#getAllStudentsBtnId")
							.click(
									function(event) {

										checked = $(".dbType:checked").length;

										if (!checked) {
											alert("You must check at least one checkbox.");
											return false;
										}

										event.preventDefault();
										var dbTypes = [];

										$(".dbType:checked").each(
												function() {
													dbTypes.push("dbTypes="
															+ $(this).val()
															+ "&");
												});

										dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
												.slice(0, -1);
										console.log(dbTypes);
										var result = dbTypes.join("");
										console.log(result);
										ajaxGet(result);
									});

					// DO GET
					function ajaxGet(result) {
						$
								.ajax({
									type : "GET",
									url : window.location + "api/student/all?"
											+ result,
									success : function(result) {
										if (result != null
												&& result.massage == "Success") {
											$('#resultDivAll ul').empty();
											$('#resultDivMONGO ul').empty();
											$('#resultDivMYSQL ul').empty();
											var custList = "";
											$
													.each(
															result.students.students,
															function(i, student) {
																var student = "- Student with Id = "
																		+ student.id
																		+ ", firstname = "
																		+ student.name
																		+ ", age = "
																		+ student.age
																		+ ", grade = "
																		+ student.grade
																		+ "<br>";
																$(
																		'#resultDivAll .list-result')
																		.append(
																				student);
															});
											console.log("Success: ", result);
										} else if (result != null
												&& result.massage == "ConnectionProblem") {
											$
													.each(
															result.dbTypes,
															function(i, dbType) {
																switch (dbType) {
																case "MYSQL":
																	$(
																			'#resultDivMYSQL ul')
																			.empty();
																	$(
																			'#resultDivMYSQL .list-result')
																			.append(
																					"MYSQL Database is not available");
																	break;
																case "MONGO":
																	$(
																			'#resultDivMONGO ul')
																			.empty();
																	$(
																			'#resultDivMONGO .list-result')
																			.append(
																					"MYSQL Database is not available");
																	break;
																default:
																	$(
																			'#resultDivAll .list-result')
																			.append(
																					"Something went wrong");
																}
															})
											$('#resultDivAll ul').empty();
											$('#resultDivAll .list-result')
													.append(
															"<strong>Connection problem - DB is not available</strong>");
											console.log("Fail: ", result);
										}
									},
									error : function(e) {
										$('#resultDivAll ul').empty();
										$('#resultDivAll .list-result')
												.append(
														"<strong>Connection problem</strong>");
										console.log("ERROR: ", e);
									}
								});
					}

				})