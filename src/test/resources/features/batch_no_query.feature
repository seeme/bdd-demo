Feature: 批號查詢
  作為使用者且成功登入後
  我想要執行批號查詢
  以便能夠查看批號相關內容

  Background:
    Given 我已經成功登入
      | username | password     |
      | sammy | 1234 |

  Scenario Outline: 有效的批號查詢
    Given 我在批號查詢頁面
    When 輸入批號 "<batch_no>"
    And 我點擊查詢按鈕
    Then 我會看到批號查詢結果，包含批號 "<batch_no_result>"
    And 目前狀態 "<status>"
    And 出現明細按鈕
    And 自有部位 "<self_position>"
    And 附條件買回部位 "<buyback_position>"
    And 票券類別送存登錄方式 "<stoke_type>"
    And 發票人 "<issuer>"
    And 發票日 "<issue_date>"
    And 票載到期日 "<due_date>"
    And 總發行面額 "<total_amount>"
    And 票卷附條件買回交易 "<buyback_transactions>"

    Examples:
      | batch_no     | batch_no_result | status   | self_position  | buyback_position | stoke_type | issuer             | issue_date | due_date  | total_amount   | buyback_transactions |
      | 060001000111 | 060001000111    | 可於次級市場交易 | TWD500,000,000 | TWD300,000,000   | CP2/ 電子登錄   | 27742385\|XX股份有限公司 | 111/08/26  | 111/11/24 | TWD800,000,000 | R221028000001,R221031000002 |


  Scenario Outline: 無效的批號查詢
    Given 我在批號查詢頁面
    And 輸入批號 "<batch_no>"
    When 我點擊查詢按鈕
    Then 我會看到批號查詢結果，包含批號 "<batch_no_result>"
    And 目前狀態 "<status>"
    And 不顯示明細按鈕
    And 自有部位 "<self_position>"
    And 附條件買回部位 "<buyback_position>"
    And 票券類別送存登錄方式 "<stoke_type>"
    And 發票人 "<issuer>"
    And 發票日 "<issue_date>"
    And 票載到期日 "<due_date>"
    And 總發行面額 "<total_amount>"
    And 票卷附條件買回交易 "<buyback_transactions>"

    Examples:
      | batch_no     | batch_no_result | status | self_position | buyback_position | stoke_type | issuer | issue_date | due_date | total_amount | buyback_transactions |
      | 060001000110 | 無此資料         |        |               |                  |            |        |            |          |              |                      |
