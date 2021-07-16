package com.spring.board.common;

import com.spring.board.dto.CommonDto;
import com.spring.board.form.CommonForm;
 
/**
 * ������ �׺���̼� ���� ������ ���� Ŭ����
 */
public class PagingUtil {
 
    public static CommonDto setPageUtil(CommonForm commonForm) {
 
        CommonDto commonDto = new CommonDto();
 
        String pagination = ""; // ����¡ ��� ��
        String functionName = commonForm.getFunction_name(); // ����¡ ����� ��û�ϴ� �ڹٽ�ũ��Ʈ �Լ���
        int currentPage = commonForm.getCurrent_page_no(); // ���� ������ ��ȣ
        int countPerList = commonForm.getCount_per_list(); // �� ȭ�鿡 ��µ� �Խù� ��
        int countPerPage = commonForm.getCount_per_page(); // �� ȭ�鿡 ��µ� ������ ��
        int totalListCount = commonForm.getTatal_list_count(); // �� �Խù� ��
        int totalPageCount = totalListCount / countPerList; // �� ������ ��
        if (totalListCount % countPerList > 0) { // �� ���̼��� ���� �� int������ ����ϸ� �������� �ִ� ��� �Խù��� �����ϱ� ������ �� �������� ���� ����
            totalPageCount = totalPageCount + 1;
        }
 
        int viewFirstPage = (((currentPage - 1) / countPerPage) * countPerPage) + 1; // �� ȭ�鿡 ù ������ ��ȣ
        int ViewLastPage = viewFirstPage + countPerPage - 1; // �� ȭ�鿡 ������ ������ ��ȣ
        if (ViewLastPage > totalPageCount) { // ������ �������� ���� �� �������� ������ ū ���� �Խù��� �������� �ʱ� ������ ������ �������� ���� ����
            ViewLastPage = totalPageCount;
        }
 
        int totalFirstPage = 1; // ��ü ������ �߿� ó�� ������
        int totalLastPage = totalPageCount; // ��ü ������ �߿� ������ ������
        int prePerPage = 0; // ���� ȭ�鿡 ù��° ��ȣ
        if (viewFirstPage - countPerPage > 0) {
            prePerPage = viewFirstPage - countPerPage;
        } else {
            prePerPage = totalFirstPage;
        }
        int nextPerPage = 0; // ���� ȭ�鿡 ù��° ��ȣ
        if (viewFirstPage + countPerPage < totalPageCount) {
            nextPerPage = viewFirstPage + countPerPage;
        } else {
            nextPerPage = totalPageCount;
        }
 
        // ������ ���̰��̼� ����
        pagination += "<div class='pagination'>";
        pagination += "<a href='javascript:" + functionName + "(\"" + totalFirstPage + "\");' class=\"direction_left01\">[<<]</a>";
        pagination += "<a href='javascript:" + functionName + "(" + prePerPage + ");' class=\"direction_left01\">[<]</a>";
        for (int a = viewFirstPage; a <= ViewLastPage; a++) {
            if (a == currentPage) {
                pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");' class='onpage'>[" + a + "]</a>";
            } else {
                pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");'>[" + a + "]</a>";
            }
        }
        pagination += "<a href='javascript:" + functionName + "(" + nextPerPage + ");' class=\"direction_right01\">[>]</a>";
        pagination += "<a href='javascript:" + functionName + "(" + totalLastPage + ");' class=\"direction_right01\">[>>]</a>";
        pagination += "</div>";
 
        int offset = ((currentPage - 1) * countPerList); // �� ȭ���� ǥ��Ǵ� �Խù��� ���� ��ȣ (���� ������)
 
        // LIMIT�� ������ row�� ��, OFFSET�� �� ��° row���� ���������� ����
        commonDto.setLimit(countPerList);
        commonDto.setOffset(offset);
        commonDto.setPagination(pagination);
 
        return commonDto;
    }
}