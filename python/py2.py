from typing import Dict, List, Union


def extract_fields(string) -> Dict[str, Union[int, str]]:
    # string is of the form '1-3 a: abcde'
    times, character, password = string.split(" ")
    min_occurrence, max_occurrence = times.split("-")

    if len(character) != 2:
        raise ValueError(f"Malformed input: {string}")

    return {"min": int(min_occurrence),
            "max": int(max_occurrence),
            "char": character[0],
            "password": password}


def test_data() -> List[Dict[str, Union[int, str]]]:
    data = """1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc""".split("\n")

    return [extract_fields(string) for string in data]


print(f"Test data: {test_data()}")


def input_data(file="data/input-day2.txt") -> List[Dict[str, Union[int, str]]]:
    with open(file, 'r') as f:
        lst = f.readlines()

    return [extract_fields(elt.strip()) for elt in lst]


print(f"Input data: {input_data()[:3]}")


def get_char_counts(string) -> Dict[str, int]:
    result = {}
    for char in string:
        if char in result.keys():
            result[char] += 1
        else:
            result[char] = 1

    return result


# print(f"'aaab': {get_char_counts('aaab')}")  # should give {"a": 3, "b": 1}


def check_validity_first(password: Dict[str, Union[int, str]]) -> bool:
    char_occurrence = get_char_counts(password["password"])
    outcome = False
    try:
        if password["min"] <= char_occurrence[password["char"]] <= password["max"]:
            outcome = True
    except KeyError:
        # if character isn't present in the password
        pass

    return outcome


def check_validity_second(password: Dict[str, Union[int, str]]) -> bool:
    password_char = password["char"]
    outcome = False
    try:
        first_char = password["password"][password["min"] - 1]
        second_char = password["password"][password["max"] - 1]
        if (((first_char is password_char) and (second_char is not password_char))
                or ((first_char is not password_char) and (second_char is password_char))):
            outcome = True
    except IndexError:
        pass

    return outcome


def valid_passwords(data: List[Dict[str, Union[int, str]]],
                    print_test: bool = False) -> None:
    total_valid_first_policy = 0
    total_valid_second_policy = 0
    for password in data:
        if check_validity_first(password):
            if print_test:
                print(f"First valid: {password}")
            total_valid_first_policy += 1
        else:
            if print_test:
                print(f"First invalid: {password}")

        if check_validity_second(password):
            if print_test:
                print(f"Second valid: {password}")
            total_valid_second_policy += 1
        else:
            if print_test:
                print(f"Second invalid: {password}")

    print(f"First policy: {total_valid_first_policy} passwords (out of {len(data)}) are valid.")
    print(f"Second policy: {total_valid_second_policy} passwords (out of {len(data)}) are valid.")


# test data
valid_passwords(test_data(), print_test=True)

# input data
valid_passwords(input_data())
