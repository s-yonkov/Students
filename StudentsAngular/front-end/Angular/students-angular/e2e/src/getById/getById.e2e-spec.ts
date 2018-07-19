import { AppPage } from '../app.po';
describe('Test get by Id page', () => {
    let page: AppPage;

    beforeEach(() => {
        page = new AppPage();
    });

    // Check if a button is present on the "show by ID" page.
    it('should display button to select student by id', () => {
        page.navigateToIdPage();

        expect((page.getShowStudentByIDButton().isPresent));
    });

    // Clicking the button "Show Student" without selecting DB - should expect alert message to select at least one DB.
    it('should dispaly error message to choose at least one checkbox when there are no selected checkboxes', () => {
        page.navigateToIdPage();

        page.getShowStudentByIDButton().click();
        expect(page.getAlert().getText()).toEqual('You must check at least one checkbox.');
        page.getAlert().accept();
    });

    // Select Mongo database and click on the Show student button - should expect alert message to insert ID.
    it('should display error message to insert id in the input field', () => {
        page.navigateToIdPage();

        const mongoCheckbox = page.getMongoCheckbox();
        mongoCheckbox.click();

        page.getShowStudentByIDButton().click();
        expect(page.getAlert().getText()).toEqual('Please insert ID');
        page.getAlert().accept();
    });

    // Select both databases and search for ID, the ID is presented only in the Mongo DB so we should receive
    // the info from Mongo and message that the Id is not presented in Mysql database.
    it('should display info for student for Mongo and mesage for Mysql that there is no such id', () => {
        page.navigateToIdPage();

        const mongoCheckbox = page.getMongoCheckbox();
        const mysqlCheckbox = page.getMysqlCheckbox();
        mongoCheckbox.click();
        mysqlCheckbox.click();

        const inputField = page.getIdInputField();

        inputField.sendKeys('99');
        page.getShowStudentByIDButton().click();

        expect(page.getMongoTable().isDisplayed());
        expect(page.getErrorDiv().isDisplayed);
        expect(page.getErrorParagraph().getText()).toEqual('No such ID');
    });
});
