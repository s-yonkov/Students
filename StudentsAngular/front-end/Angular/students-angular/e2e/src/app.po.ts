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

  getAlertMessage() {
    const alert = browser.switchTo().alert().getText();
    return alert;
  }


}
