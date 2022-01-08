CREATE OR REPLACE TYPE T_BONOSTBL IS TABLE OF NUMBER(6); -- 삭제할 글번호들을 저장할 변수의 타입

CREATE OR REPLACE TYPE T_SAVENAMESTBL IS TABLE OF VARCHAR2(70); -- 삭제할 파일들의  ATT_SAVENAME을 저장할 변수의 타입

CREATE OR REPLACE PROCEDURE PL_DEL_FREEBOARD(
    BONO IN NUMBER,
    SAVENAMES OUT T_SAVENAMESTBL, -- 삭제 대상 게시글의 첨부파일 저장 명(N)
    ROWCNT OUT NUMBER -- 삭제된 게시글수
)
IS
V_DELBONOS T_BONOSTBL;
BEGIN
    -- 삭제 대상 글번호 조회
    SELECT BO_NO BULK COLLECT INTO V_DELBONOS
    FROM FREEBOARD 
    START WITH BO_NO = BONO
    CONNECT BY PRIOR BO_NO = BO_PARENT;
    -- 삭제 대상 파일의 저장명 조회
    SELECT ATT_SAVENAME BULK COLLECT INTO SAVENAMES
    FROM ATTATCH
    WHERE BO_NO IN (
        SELECT * FROM TABLE(V_DELBONOS)
    );
    -- 삭제 대상 게시글 수 
    ROWCNT := V_DELBONOS.COUNT;
    -- 파일 메타데이터 삭제
    DELETE FROM ATTATCH
    WHERE BO_NO IN (
        SELECT * FROM TABLE(V_DELBONOS)
    );
    -- 덧글 삭제
    DELETE FROM FREEREPLY
    WHERE BO_NO IN (
        SELECT * FROM TABLE(V_DELBONOS)
    );
    -- 게시글 삭제
    DELETE FROM FREEBOARD
    WHERE BO_NO IN (
        SELECT * FROM TABLE(V_DELBONOS)
    );
END;

-- 테스트용 익명 블럭
DECLARE
  BONO NUMBER(6);
  SAVENAMES T_SAVENAMESTBL;
  ROWCNT NUMBER(3);
BEGIN
  BONO := 615;
  PL_DEL_FREEBOARD(BONO, SAVENAMES, ROWCNT);  
  DBMS_OUTPUT.PUT_LINE(ROWCNT||'개 게시글 삭제');
  IF SAVENAMES IS NOT NULL AND SAVENAMES.COUNT > 0 THEN
      FOR IDX IN SAVENAMES.FIRST..SAVENAMES.LAST LOOP
        DBMS_OUTPUT.PUT_LINE(SAVENAMES(IDX));
      END LOOP;
  END IF;
  ROLLBACK;
END;
