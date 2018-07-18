import { AppPage } from './app.po';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

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
