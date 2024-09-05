import Foundation

func readInput() -> (Int, Int, [[Int]]) {
    let nm = readLine()!.split(separator: " ").map { Int($0)! }
    let N = nm[0]
    let M = nm[1]
    
    var classes: [[Int]] = []
    
    for _ in 0..<N {
        let abilities = readLine()!.split(separator: " ").map { Int($0)! }.sorted()
        classes.append(abilities)
    }
    return (N, M, classes)
}


func solution(_ N: Int, _ M: Int, _ classes: [[Int]]) -> Int {
    var pointers = [Int](repeating: 0, count: N)
    
    var selected: [Int] = classes.enumerated().map { $0.element[0] }
    
    var minDiff = Int.max
    
    while true {
        let minValue = selected.min()!
        let maxValue = selected.max()!
        
        minDiff = min(minDiff, maxValue - minValue)
        
        var minClassIndex = -1
        for i in 0..<N {
            if selected[i] == minValue {
                minClassIndex = i
                break
            }
        }
        if pointers[minClassIndex] == M - 1 {
            break
        }
        
        pointers[minClassIndex] += 1
        selected[minClassIndex] = classes[minClassIndex][pointers[minClassIndex]]
    }
    
    return minDiff
}

func main() {
    let (N, M, classes) = readInput()
    let result = solution(N, M, classes)
    print(result)
}

main()