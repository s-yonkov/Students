import { AppPage } from '../app.po';
describe('Test save student in DB', () => {
    let page: AppPage;

    beforeEach(() => {
        page = new AppPage();
    });

    // Check for active element.
    it('should display button to select student by id', () => {
        page.navigateToSavePage();

        page.getSaveStudentButton().click();

        expect((page.getActiveElement().getText())).toEqual('! Please fill out this field.');
    });



});
