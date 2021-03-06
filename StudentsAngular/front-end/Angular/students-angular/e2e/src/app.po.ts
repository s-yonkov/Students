import { browser, by, element, protractor } from 'protractor';

export class AppPage {
  navigateToHomePage() {
    return browser.get('/');
  }

  navigateToIdPage() {
    return browser.get('/byId');
  }

  navigateToSavePage() {
    return browser.get('/save');
  }

  getParagraphText() {
    return element(by.css('app-root h5')).getText();
  }

  getShowStudentsButton() {
    return element(by.cssContainingText('button', 'Show students'));
  }

  getMongoCheckbox() {
    return element(by.cssContainingText('.custom-control-label', 'Mongo'));
  }

  getMysqlCheckbox() {
    return element(by.cssContainingText('.custom-control-label', 'MySQL'));
  }

  getAlert() {
    const alert = browser.switchTo().alert();
    return alert;
  }

  getMongoTable() {
    return element(by.cssContainingText('table.table-bordered.table-hover', 'Mongo'));
  }

  getMySQLTable() {
    return element(by.cssContainingText('table.table-bordered.table-hover', 'MySQL'));
  }

  checkIfMysqlTableIsPresented() {
    if (element(by.cssContainingText('table.table-bordered.table-hover', 'MySQL')).isPresent()) {
      return true;
    } else {
      return false;
    }
  }

  checkIfMongolTableIsPresented() {
    if (element(by.cssContainingText('table.table-bordered.table-hover', 'Mongo')).isPresent()) {
      return true;
    } else {
      return false;
    }
  }

  getShowStudentByIDButton() {
    return element(by.cssContainingText('button', 'Show student'));
  }

  getIdInputField() {
    return element(by.css('#searchId'));
  }

  getErrorDiv() {
    return element(by.css('.errMsg'));
  }

  getErrorParagraph() {
    return element(by.css('.errParagraph'));
  }

  getActiveElement() {
    const active = browser.switchTo().activeElement();
    return active;
  }

  getSaveStudentButton() {
    return element(by.cssContainingText('button', 'Add Student'));
  }

  getIdInput() {
    return element(by.css('#id'));
  }

  getValidations() {
    return element.all(by.css('.alert-danger'));
  }

  getNameInput() {
    return element(by.css('#name'));
  }

  getAgeInput() {
    return element(by.css('#age'));
  }

  getGradeInput() {
    return element(by.css('#grade'));
  }

  refresgPage() {
    browser.refresh();
  }

  pouseBrowser() {
    browser.pause(5000);
  }

  checkIftSuccessfullAddInMongo() {
    return element(by.cssContainingText('.mongo-success', 'Successfully added in:')).isDisplayed();
  }

  checkIfSuccessfullAddInMySQL() {
    return element(by.cssContainingText('.mysql-success', 'Successfully added in:')).isDisplayed();
  }

}
