import { AppPage } from './app.po';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  // Home page tests:

  // Clicking on Mongo from the checkbox and checking if it is selected
  it('should return if Mongo is selected from the checkbox', () => {
    page.navigateToHomePage();

    const checkbox = page.getMongoCheckbox();
    checkbox.click();

    expect(checkbox.isSelected());
  });

  // Clicking on MySQL from the checkbox and checking if it is selected
  it('should return if MySQL is selected from the checkbox', () => {
    page.navigateToHomePage();

    const checkbox = page.getMysqlCheckbox();
    checkbox.click();

    expect(checkbox.isSelected());
  });

  // Clicking on Mongo checkbox and on the button and checking if the table with the results is shown
  it('should show only Mongo table with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mongoCheckbox.click();
    expect(!mysqlCheckbox.isSelected());

    page.getShowStudentsButton().click();

    expect(page.getMongoTable().isDisplayed());
    expect(!page.checkIfMysqlTableIsPresented());
  });

  // Clicking on MySQL checkbox and on the button and checking if the table with the results is shown
  it('should show only MySQL table with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mysqlCheckbox.click();
    expect(!mongoCheckbox.isSelected());

    page.getShowStudentsButton().click();

    expect(page.getMySQLTable().isDisplayed());
    expect(!page.checkIfMongolTableIsPresented());
  });

  // Clicking on All checkboxes and on the button and checking if all tables with results are shown
  it('should show all tables with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mysqlCheckbox.click();
    mongoCheckbox.click();

    page.getShowStudentsButton().click();

    expect(page.getMySQLTable().isDisplayed());
    expect(page.getMongoTable().isDisplayed());
  });

  // Clicking on All checkboxes and than only on Mongo
  // and on the button and checking if only Mongo table with results is shown
  it('should show only Mongo table with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mysqlCheckbox.click();
    mongoCheckbox.click();
    mysqlCheckbox.click();

    page.getShowStudentsButton().click();

    expect(page.getMongoTable().isDisplayed());
    expect(!page.checkIfMysqlTableIsPresented());
  });

  // Clicking on All checkboxes and than only on MySQL
  // and on the button and checking if only MySQL table with results is shown
  it('should show only MySQL table with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mysqlCheckbox.click();
    mongoCheckbox.click();
    mongoCheckbox.click();

    page.getShowStudentsButton().click();

    expect(page.getMySQLTable().isDisplayed());
    expect(!page.checkIfMongolTableIsPresented());
  });

  // Clicking on All checkboxes and than on the button and only on MySQL
  // and on the button again and checking if only Mongo table with results is shown
  it('should show only Mongo table with results', () => {
    page.navigateToHomePage();

    const mongoCheckbox = page.getMongoCheckbox();
    const mysqlCheckbox = page.getMysqlCheckbox();

    mysqlCheckbox.click();
    mongoCheckbox.click();

    page.getShowStudentsButton().click();

    mysqlCheckbox.click();

    page.getShowStudentsButton().click();

    expect(page.getMongoTable().isDisplayed());
    expect(!page.checkIfMysqlTableIsPresented());
  });

  // Checking if text is presented on the Home page
  it('should display message to choose database', () => {
    page.navigateToHomePage();
    expect(page.getParagraphText()).toEqual('Please choose database:');
  });

  // Clicking the button "Show All Students" without selecting DB - should expect alert message to select at least one DB
  it('should dispaly error mesage to choose at least one checkbox when there are no selected checkboxes', () => {
    page.navigateToHomePage();

    page.getShowStudentsButton().click();
    expect(page.getAlertMessage()).toEqual('You must check at least one checkbox.');
  });
});
