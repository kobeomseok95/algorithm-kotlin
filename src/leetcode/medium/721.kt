package leetcode.medium

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Constraints:
 * - 1 <= accounts.length <= 1000
 * - 2 <= accounts[i].length <= 10
 * - 1 <= accounts[i][j].length <= 30
 * - accounts[i][0] consists of English letters.
 * - accounts[i][j] (for j > 0) is a valid email.
 *
 * 시간 복잡도: O(N^3) (intersect 연산)
 * 공간 복잡도: O(N)
 */
class `721` {
    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
        val emailNameMap = getEmailAccountsMap(accounts)
        return emailNameMap.entries.map { (emails, name) ->
            listOf(name).plus(emails.sorted())
        }
    }

    private fun getEmailAccountsMap(accounts: List<List<String>>): Map<Set<String>, String> {
        val map = mutableMapOf<Set<String>, String>()
        accounts.forEach { accountEmails ->
            val name = accountEmails[0]
            val emails = accountEmails.subList(1, accountEmails.size).toSet()
            val intersectedKeys = map.keys
                .filter { emailsKey -> emailsKey.intersect(emails).isNotEmpty() }
            if (intersectedKeys.isEmpty()) {
                map[emails] = name
            } else {
                intersectedKeys.forEach { key -> map.remove(key) }
                map[intersectedKeys.flatten().toSet().plus(emails)] = name
            }
        }
        return map
    }
}

fun main() {
    val solution = `721`()
    println(solution.accountsMerge(listOf(listOf("David", "David0@m.co", "David1@m.co"), listOf("David", "David3@m.co", "David4@m.co"), listOf("David", "David4@m.co", "David5@m.co"), listOf("David", "David2@m.co", "David3@m.co"), listOf("David", "David1@m.co", "David2@m.co")))) // [["David","David0@m.co","David1@m.co","David2@m.co","David3@m.co","David4@m.co","David5@m.co"]]
    println(solution.accountsMerge(listOf(listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"), listOf("John", "johnsmith@mail.com", "john00@mail.com"), listOf("Mary", "mary@mail.com"), listOf("John", "johnnybravo@mail.com")))) // [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    println(solution.accountsMerge(listOf(listOf("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"), listOf("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"), listOf("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"), listOf("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"), listOf("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")))) // [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
}
