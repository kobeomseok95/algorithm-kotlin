문제 링크로부터 Kotlin 보일러플레이트를 생성합니다.

## 실행 절차

1. 주어진 URL에서 플랫폼을 판별한다 (leetcode.com / programmers.co.kr)
2. CLAUDE.md의 "문제 데이터 가져오기" 섹션에 따라 curl로 문제 정보를 가져온다
3. 응답에서 문제 ID, 난이도, 설명, Constraints, 함수 시그니처, Examples를 추출한다
4. CLAUDE.md의 "코드 생성 규칙"에 따라 Kotlin 파일을 생성한다
5. 생성된 파일 경로만 간단히 출력한다

URL: $ARGUMENTS

## 주의

- 함수 body는 `TODO("")` 만 작성한다.
- 추가 설명이나 대화 없이 파일 생성 후 경로만 출력한다.
