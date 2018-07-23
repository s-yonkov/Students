import { AppPage } from '../app.po';
describe('Test save student in DB', () => {
    let page: AppPage;

    beforeEach(() => {
        page = new AppPage();
    });

    it('should check if button is present and inactive(disabled)', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });

    // Insert value in the input field out of the range and check if there is a validation/error message
    // and if the submit button is disabled
    it('should check for validation for the id input field', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const idInput = page.getIdInput();
        idInput.sendKeys(2147483648);

        const validationList = page.getValidations();

        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('ID should be in range 1 - 2147483647!');

        idInput.clear();
        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('ID is required!');

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });

    // Enter invalid name and check for validation
    it('should check validation for name', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const nameInput = page.getNameInput();
        nameInput.sendKeys('A');

        const validationList = page.getValidations();

        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('Name should be at least 2 characters long!');

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });


    // Insert value in the age input field out of the range and check if there is a validation/error message
    // and if the submit button is disabled
    it('should check for validation for the age input field', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const ageInput = page.getAgeInput();
        ageInput.sendKeys(2);

        const validationList = page.getValidations();

        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('Age should be in range 16 - 99!');

        ageInput.clear();
        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('Age is required!');

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });

    // Insert value in the grade input field out of the range and check if there is a validation/error message
    // and if the submit button is disabled
    it('should check for validation for the grade input field', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const gradeInput = page.getGradeInput();
        gradeInput.sendKeys(16);

        const validationList = page.getValidations();

        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('Grade should be in range 2 - 6!');

        gradeInput.clear();
        expect(validationList.count()).toBe(1);
        expect(validationList.get(0).getText()).toBe('Grade is required!');

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });


    // Insert invalid inputs for all fields and check if there are validation/error messages for them
    // and if the submit button is disabled
    it('should check for validation for all fields at the same time', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const idInput = page.getIdInput();
        const nameInput = page.getNameInput();
        const ageInput = page.getAgeInput();
        const gradeInput = page.getGradeInput();

        idInput.sendKeys(2147483648);
        nameInput.sendKeys('a');
        ageInput.sendKeys(1);
        gradeInput.sendKeys(-4);

        const validationList = page.getValidations();

        expect(validationList.count()).toBe(4);
        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');

        idInput.clear();
        nameInput.clear();
        ageInput.clear();
        gradeInput.clear();

        expect(button.isPresent());
        expect(button.getAttribute('disabled')).toBe('true');
    });

    // Insert valid input for all fields without selecting database
    // and check if there is a validation/error message
    // and if the submit button is disabled
    it('should check for validation for databasese selection', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const idInput = page.getIdInput();
        const nameInput = page.getNameInput();
        const ageInput = page.getAgeInput();
        const gradeInput = page.getGradeInput();

        idInput.sendKeys(565656);
        nameInput.sendKeys('Simeon');
        ageInput.sendKeys(22);
        gradeInput.sendKeys(5);

        expect(page.getValidations().isDisplayed());

        expect(button.isPresent());
        expect(button.isEnabled());

        button.click();
        expect(page.getAlert().getText()).toEqual('You must check at least one checkbox.');
        page.getAlert().accept();
    });

    // Insert valid input for all fields and selecting both databasese and checking for the result
    it('should check if recording in both dbs is working', () => {
        page.navigateToSavePage();

        const button = page.getSaveStudentButton();

        const idInput = page.getIdInput();
        const nameInput = page.getNameInput();
        const ageInput = page.getAgeInput();
        const gradeInput = page.getGradeInput();

        idInput.sendKeys(989898);
        nameInput.sendKeys('John');
        ageInput.sendKeys(25);
        gradeInput.sendKeys(5);

        const mongoCheckbox = page.getMongoCheckbox();
        const mysqlCheckbox = page.getMysqlCheckbox();
        mongoCheckbox.click();
        mysqlCheckbox.click();

        expect(page.getValidations().isDisplayed());

        expect(button.isPresent());
        expect(button.isEnabled());

        button.click();

        expect(page.getMongoTable().isDisplayed());
        expect(page.getMySQLTable().isDisplayed());
        expect(page.checkIftSuccessfullAddInMongo());
        expect(page.checkIfSuccessfullAddInMySQL());
    });

});
