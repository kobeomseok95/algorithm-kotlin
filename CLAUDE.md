# Algorithm Scaffold Generator

알고리즘 문제 링크를 받으면 Kotlin 보일러플레이트 코드를 자동 생성합니다.

## 문제 데이터 가져오기

### LeetCode

아래 curl로 GraphQL API를 호출하여 문제 정보를 가져온다.
URL에서 slug를 추출한다. (예: `problems/two-sum/` → `two-sum`)

```bash
curl -s -X POST https://leetcode.com/graphql \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query($titleSlug:String!){question(titleSlug:$titleSlug){questionId title difficulty content codeSnippets{langSlug code}}}",
    "variables": {"titleSlug": "SLUG_HERE"}
  }'
```

응답 JSON에서 추출할 필드:
- `questionId`: 문제 번호 → 클래스명
- `difficulty`: 난이도 → 패키지 (Easy/Medium/Hard → easy/medium/hard)
- `content`: HTML → 문제 설명, Constraints, Examples 파싱
- `codeSnippets`: `langSlug === "kotlin"` 항목에서 함수 시그니처 추출

HTML 파싱 시:
- 문제 설명: `<p>` 태그들에서 "Example" 이전까지의 텍스트
- Constraints: "Constraints:" 이후 `<li>` 태그들
- Examples: "Input:", "Output:" 패턴으로 추출

### Programmers

```bash
curl -s 'https://school.programmers.co.kr/learn/courses/30/lessons/LESSON_ID' \
  -H "User-Agent: Mozilla/5.0" \
  -H "Accept-Language: ko-KR,ko;q=0.9"
```

HTML에서 추출:
- 문제 ID: URL의 lessons/{id}
- 난이도: `Lv.N` 패턴 → `lvN`
- 문제 설명: `.markdown` 또는 `.challenge_description` 영역
- 제한사항: "제한" 텍스트 이후 `<li>` 항목들
- 입출력 예시: `<table>` 에서 추출
- 함수 시그니처: 페이지 내 Kotlin 코드 블록에서 추출. 없으면 다른 언어를 참고하여 Kotlin으로 변환

## 코드 생성 규칙

### 1. 파일 위치

| 플랫폼 | 패키지 | 파일 경로                             |
|---------|--------|-----------------------------------|
| LeetCode | `leetcode.{difficulty}` | `src/leetcode/medium/11.kt`       |
| Programmers | `programmers.{level}` | `src/programmers/level2/42862.kt` |

디렉토리가 없으면 자동 생성한다.

### 2. 클래스명

- 문제 ID를 백틱으로 감싼다: `` `11` ``, `` `42862` ``

### 3. Javadoc 주석

```kotlin
/**
 * {문제 설명 원문 그대로 - 요약하지 않는다}
 *
 * Constraints:
 * - {제약조건1}
 * - {제약조건2}
 *
 * 시간 복잡도: TODO("")
 * 공간 복잡도: TODO("")
 */
```

### 4. 함수

- 함수 시그니처는 LeetCode/Programmers에서 가져온 것을 그대로 사용
- body는 반드시 `TODO("")` 만 작성
- **절대 풀이를 작성하지 않는다**

### 5. main 함수

```kotlin
fun main() {
    val solution = `{ID}`()
    println(solution.{함수명}({example_input})) // {expected_output}
}
```

- 문제의 모든 Example 포함
- 주석으로 expected output 명시

### 6. Kotlin 타입 변환

| 문제 타입 | main 표현 |
|-----------|----------|
| int[] | `intArrayOf(1,2,3)` |
| int[][] | `arrayOf(intArrayOf(1,2), intArrayOf(3,4))` |
| String[] | `arrayOf("a", "b")` |
| List<Int> | `listOf(1, 2, 3)` |
| String | `"abc"` |
| char | `'a'` |
| char[] | `charArrayOf('a','b')` |

### 7. 전체 템플릿

```kotlin
package {platform}.{difficulty}

/**
 * {문제 설명 원문}
 *
 * Constraints:
 * - {constraint}
 *
 * 시간 복잡도: TODO("")
 * 공간 복잡도: TODO("")
 */
class `{id}` {
    fun {name}({params}): {returnType} {
        TODO("")
    }
}

fun main() {
    val solution = `{id}`()
    println(solution.{name}({input1})) // {output1}
    println(solution.{name}({input2})) // {output2}
}
```

## 중요

- 함수 body는 `TODO("")` 만 작성한다.
- 문제 설명 원문 그대로 사용한다.
- 파일 생성 후 생성된 파일 경로만 간단히 알려준다.
