

BRANCH=$(sed -n 's/.*\[\(.*\)\][ ]*\<=====.*/\1/p' ./wiki/TrackSpark/data/TrackSpark.wiki)
read -p "Current branch: $BRANCH [OK?]"

#TASKS=$(sed -n 's/\-\(.*\)/\1/p' ./wikilexitree/TrackSpark/$BRANCH.wiki)
TASKS=$(cat ./wiki/TrackSpark/data/$BRANCH.wiki)

old=$IFS
IFS='-'
tokens=( $TASKS )
TASK=${tokens[1]};
IFS=$old

NUMS=$(sed -n 's/\*DONE c:\([0-9][0-9]*\).*/\1/p' ./wiki/TrackSpark/data/$BRANCH.wiki)

old=$IFS
IFS='
'
tokens=( $NUMS )
#NUMSA=${tokens[1]};
LAST_NUM=${tokens[${#tokens[@]} - 1]}
IFS=$old



LAST_NUM=$(($LAST_NUM + 1))

MSG=$(echo "$BRANCH [$LAST_NUM] $TASK")

MSGP=$(echo -e "$BRANCH [$LAST_NUM]\n $TASK")


read -p "Current task: $MSGP"


cp  ./lexitree/assets/new_lexitree*.db ~/development/altres/ 
git add -A;
git commit -m "$MSG"; 
touch /Users/laullobetpayas/git/lexitree/ioslexitree/DictioTest/Resources/test.db; 
cp ~/development/altres/new_lexitree*.db  /Users/laullobetpayas/git/lexitree/lexitree/assets/ ; 
git push origin

read -p "SAVE task? [OK?]"

sed -e "1,/\(\-\)/s/\(\-\)/c:$LAST_NUM/" <./wikilexitree/TrackSpark/$BRANCH.wiki > ./wikilexitree/TrackSpark/tmp.$BRANCH.wiki

cp ./wikilexitree/TrackSpark/tmp.$BRANCH.wiki ./wikilexitree/TrackSpark/$BRANCH.wiki

#read -p "step2"

sed -e "s/c:$LAST_NUM.*/\*DONE &\*/" <./wikilexitree/TrackSpark/$BRANCH.wiki > ./wikilexitree/TrackSpark/tmp.$BRANCH.wiki

#read -p "step3"

cp ./wikilexitree/TrackSpark/tmp.$BRANCH.wiki ./wikilexitree/TrackSpark/$BRANCH.wiki

rm ./wikilexitree/TrackSpark/tmp.$BRANCH.wiki

open /Applications/restartwikidpad.app
