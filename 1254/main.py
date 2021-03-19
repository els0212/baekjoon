def is_palindrome(input_str : str) -> bool:
    str_len: int = len(input_str)
    half: int = int(str_len / 2)
    for j in range(half):
        if input_str[j] != input_str[str_len - j - 1]:
            return False
    return True


if __name__ == '__main__':
    stack = []
    input_str: str = input()
    ori_len = len(input_str)
    add_count: int = 0
    if is_palindrome(input_str) is True:
        print(ori_len)
    else:
        while True:
            input_str = input_str[1:]
            add_count += 1
            if is_palindrome(input_str) is True:
                break
        print(add_count + ori_len)

