
def input_data(file="input-day1.txt"):
    with open(file, 'r') as f:
        lst = f.readlines()

    return [int(elt.strip()) for elt in lst]


def two_numbers(lst):
    size = len(lst)
    result = []
    for i in range(0, size):
        first = lst[i]
        for j in range(i, size):
            second = lst[j]
            if first + second == 2020:
                product = first * second
                result.append((first, second, product))

    return result


# test for sample data
print(f"Sample data: {two_numbers(lst=[1721, 979, 366, 299, 675, 1456])}")

# output:
#
# Sample data: [(1721, 299, 514579)]


# test for input data
print(f"Input data: {two_numbers(lst=input_data())}")

# output:
#
# Input data: [(456, 1564, 713184)]


def three_numbers(lst):
    size = len(lst)
    result = []
    for i in range(0, size):
        first = lst[i]
        for j in range(i, size):
            second = lst[j]
            for k in range(j, size):
                third = lst[k]
                if first + second + third == 2020:
                    product = first * second * third
                    result.append((first, second, third, product))

    return result


# test for sample data
print(f"Sample data: {three_numbers(lst=[1721, 979, 366, 299, 675, 1456])}")

# output:
#
# Sample data: [(979, 366, 675, 241861950)]


# test for input data
print(f"Input data: {three_numbers(lst=input_data())}")

# output:
#
# Input data: [(764, 857, 399, 261244452)]
