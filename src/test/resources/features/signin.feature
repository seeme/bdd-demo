Feature: 使用者登入
  作為一個註冊用戶
  我想要登入系統
  以便能夠使用甘丹查的功能

  Scenario: 成功登入
    Given 我在登入頁面
    When 我輸入以下有效的帳號資訊:
      | username | password     |
      | sammy | 1234 |
      | jimmy | 5678 |
    And 我點擊登入按鈕
    Then 我應該被導向到甘丹查主頁面
    And 主頁面上有一個初級市場下搭選單

  Scenario: 無效登入
    Given 我在登入頁面
    When 我輸入以下無效的帳號資訊:
      | username | password       |
      | tom  | 1111 |
    And 我點擊登入按鈕
    Then 我應該看到一則「密碼錯誤，請重新登入」錯誤訊息
    And 我應該保持在登入頁面上