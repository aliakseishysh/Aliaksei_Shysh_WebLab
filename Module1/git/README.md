# Part0
1) git init
2) touch README.md
3) vim README.md
4) git status
5) git add
6) git commit -m "initial commit"
7) git checkout -b develop <br>
git checkout -b git_task <br>
git checkout -b git_0 <br>
git show-branch
8) vim README.md + change file <br>
git add README.md <br>
git commit -m "README.md update" <br>
git checkout git_task <br>
git merge git_0 --no-ff <br>
git checkout develop <br>
git merge git_task --no-ff <br>
git checkout master <br>
git merge develop --no-ff <br>
9) gitk
# Part1
1) git checkout git_task <br>
mkdir Part1 <br>
git checkout -b git_1 <br>
git checkout git_task <br>
git checkout -b git_2 <br>
mkdir Module1 && cd Module1 <br>
mkdir git && cd git <br>
cd Part1 <br>
git checkout git_1 <br>
vim firstFile.txt + add 10 lines <br>
git add firstFile.txt <br>
git commit -m "added firstFile.txt" 
2) cp firstFile.txt secondFile.txt <br>
git add secondFile.txt <br>
git commit -m "added secondFile.txt" 
3) git checkout git_2 <br>
git merge git_1 --no-ff 
4) vim secondFile.txt + update 2 lines <br>
git add secondFile.txt <br>
git commit -m "updated secondFile.txt"
5) git chechout git_1 <br>
vim secondFile.txt + update 2 lines <br>
git add secondFile.txt <br>
git commit -m "updated secondFile.txt"
6) git merge git_2 --no-ff <br>
vim secondFile.txt + resolve <br>
git add secondFile.txt <br>
git commit -m "merged and resolved conflict"
7) vim firstFile.txt + update 2 lines <br>
git add firstFile.txt <br>
git commit -m "updated firstFile.txt, step 7"
8) vim firstFile.txt + update 2 another lines <br>
git add firstFile.txt <br>
git commit -m "updated firstFile.txt, step 8"
9) mkdir patches <br>
git format-patch -1 0aa343022ca2351cb881c24638b081b47478e332 -o patches <br>
git checkout git_2 <br>
git am patches/0001-updated-firstFile.txt-step-7.patch <br>
rm -rf patches 
10) git checkout git_1 <br>
git log -2 <br>
git checkout git_2 <br>
git cherry-pick 7f57ff86ae7f5a3bc94ca00e6fb5afa4ed2bdcbb
11) git reset --soft HEAD~2 <br>
git commit -m "concatenated the last 2 commits (updated 4 lines in the firstFile.txt)"
12) echo "Third file" > thirdFile.txt <br>
git add thirdFile.txt <br>
git commit --amend --author="Aliaksey (updated author) <abarakabama@gmail.com>" <br>
git commit --amend --date=now
13) git revert HEAD
14) echo "Third file" > thirdFile.txt <br>
git add thirdFile.txt <br>
git commit -m "added thirdFile.txt"
15) git reset --hard HEAD~2
16) git push -u origin git_2 <br>
git checkout git_1 <br>
git push -u origin git_1
17) git clone git@github.com:aliakseishysh/Aliaksei_Shysh_WebLab.git
18) git checkout git_1 <br>
vim firstFile.txt + update <br>
git add firstFile.txt <br>
git commit -m "updated firstFile.txt, step 18"
19) ... <br>
vim firstFile.txt + update
20) git stash <br>
vim firstFile.txt + update <br>
git stash <br>
git pull <br>
git stash pop <br>
git add firstFile.txt <br>
git commit -m "18 and 20 steps without 19" <br>
git push <br>
git reset --hard HEAD~1 <br>
git stash pop
#Part2
1) git checkout git_task <br>
git checkout -b git_3
2) touch doubtingFile.txt <br>
git add doubtingFile.txt <br>
git commit -m "added doubtingFile.txt"
3) vim doubtingFile.txt + add line <br>
git add doubtingFile.txt <br>
git commit -m "add line 1 to doubting file.txt" x5
4) git log -6
```
commit 307f20d4398dfa6fe93497a6e7f5e3f7b10e798e (HEAD -> git_3)
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:57:53 2021 +0300

    add line 5 to doubting file.txt

commit a4212a3c7c234f95eca658428db9bc834d5e58c0
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:57:35 2021 +0300

    add line 4 to doubting file.txt

commit 1ed14300d782df77a2633642599da4ab207f082e
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:57:18 2021 +0300

    add line 3 to doubting file.txt

commit 8743ff2d1bd9df0a9e34e21120c25801eb2f3a02
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:57:04 2021 +0300

    add line 2 to doubting file.txt

commit 0fd13e1052783296d9274c0ab748e7eedd1de5f2
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:56:49 2021 +0300

    add line 1 to doubting file.txt

commit 13a46e6333b4c3ec3158566a16389ebe363eeb1b
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:54:38 2021 +0300

    added doubtingFile.txt
```
5) git rebase -i HEAD~5  <br>
ord 0fd13e1 add line 1 to doubting file.txt <br>
ash 8743ff2 add line 2 to doubting file.txt <br>
ash 1ed1430 add line 3 to doubting file.txt <br>
ash a4212a3 add line 4 to doubting file.txt <br>
ash 307f20d add line 5 to doubting file.txt <br>

```
commit 3bf48f0e42c9ebb3895c779e72adf430a9f6f472 (HEAD -> git_3)
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:56:49 2021 +0300

    add 5 lines to doubtingFile.txt

commit 13a46e6333b4c3ec3158566a16389ebe363eeb1b
Author: Aliaksei A. Shysh <abarakabama@gmail.com>
Date:   Wed Nov 10 19:54:38 2021 +0300

    added doubtingFile.txt
```
6) Хэши разные потому, что изменяется коммит.
       Даты одинаковые в первом и результирующем коммитах потому, что squash в первый коммит.
       Сообщения коммитов разные потому что использовался reword.
7) 
```
3bf48f0 (HEAD -> git_3) HEAD@{0}: rebase (finish): returning to refs/heads/git_3
3bf48f0 (HEAD -> git_3) HEAD@{1}: rebase (squash): add 5 lines to doubtingFile.txt
6fd7b86 HEAD@{2}: rebase (squash): # This is a combination of 4 commits.
ffed277 HEAD@{3}: rebase (squash): # This is a combination of 3 commits.
d65ac7c HEAD@{4}: rebase (squash): # This is a combination of 2 commits.
40b73f1 HEAD@{5}: rebase (reword): add 5 lines to doubtingFile.txt
0fd13e1 HEAD@{6}: rebase: fast-forward
13a46e6 HEAD@{7}: rebase (start): checkout HEAD~5
307f20d HEAD@{8}: commit: add line 5 to doubting file.txt
a4212a3 HEAD@{9}: commit: add line 4 to doubting file.txt
1ed1430 HEAD@{10}: commit: add line 3 to doubting file.txt
8743ff2 HEAD@{11}: commit: add line 2 to doubting file.txt
0fd13e1 HEAD@{12}: commit: add line 1 to doubting file.txt
13a46e6 HEAD@{13}: commit: added doubtingFile.txt
```
