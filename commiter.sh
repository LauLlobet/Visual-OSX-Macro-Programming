

BRANCH=$(sed -n 's/.*\[\(.*\)\][ ]*\<=====.*/\1/p' ./wiki/TrackSpark/data/TrackSpark.wiki)
read -p "Current branch: $BRANCH [OK?]"

#TASKS=$(sed -n 's/\-\(.*\)/\1/p' ./wiki/TrackSpark/$BRANCH.wiki)
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

git add -A;
git commit -m "$MSG"; 

read -p "SAVE task? [OK?]"

sed -e "1,/\(\-\)/s/\(\-\)/c:$LAST_NUM/" <./wiki/TrackSpark/data/$BRANCH.wiki > ./wiki/TrackSpark/data/tmp.$BRANCH.wiki

cp ./wiki/TrackSpark/data/tmp.$BRANCH.wiki ./wiki/TrackSpark/data/$BRANCH.wiki

#read -p "step2"

sed -e "s/c:$LAST_NUM.*/\*DONE &\*/" <./wiki/TrackSpark/data/$BRANCH.wiki > ./wiki/TrackSpark/data/tmp.$BRANCH.wiki

#read -p "step3"

cp ./wiki/TrackSpark/TrackSpark/data/tmp.$BRANCH.wiki ./wiki/TrackSpark/data/$BRANCH.wiki

rm ./wiki/TrackSpark/data/tmp.$BRANCH.wiki

pkill -f WikidPad.py
/Applications/wikipad/run.sh
