import Foundation

func readInput() -> (Int, [[Bool]]) {
    let line = readLine()!.split(separator: " ").map{Int(String($0))!}
    let N = line[0]
    let M = line[1]
    var list = Array(repeating: Array(repeating: false, count: N + 1), count: N + 1)
   
    for _ in 0..<M {
        let line = readLine()!.split(separator: " ").map{Int(String($0))!}
        list[line[0]][line[1]] = true
        list[line[1]][line[0]] = true
    }
    return (N, list)
}

func dfs(_ N: Int, _ start: Int, _ visited: inout [Bool], _ list: [[Bool]]) {
    visited[start] = true
    for i in 1...N {
        if visited[i] == false && list[start][i] {
            dfs(N, i, &visited, list)
        }
    }
}

let (N, list) = readInput()
var visited: [Bool] = Array(repeating: false, count: N + 1)
var count = 0
for i in 1...N {
    if !visited[i] {
        count += 1
        dfs(N, i, &visited, list)
    }
}

print(count)
