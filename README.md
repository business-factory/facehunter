<h1>Face Hunter</h1>
<h4>powered by <a href="https://www.roihunter.com/join-us">ROI Hunter</a></h4>
This is an open-source Slack face guessing game. Let's be honest: it was written solely for the purpose of improving ROI Hunter brand awareness. It is based on a previous game that was developed by our Internal team in their spare time, but then got pretty popular.

<h3>Installation</h3>
Simply install the app from Slack app directory.

<h3>Running the app</h3>
Use `/facehunt` slash command in any public channel. Facehunter will register this and will send a PM to you. Nothing else needed.

<h3>How does it work?</h3>
When a user requests another guess (either via `Guess another` button or via `/facehunt` command), the app calls `users.list` <a href="https://api.slack.com/methods/users.list">Slack method</a>. It parses the output (nothing is stored) and picks 1 user with photo and four other random names and builds the guessing buttons from them. Then it calls <a href="https://api.slack.com/methods/chat.postMessage">`chat.postMessage`</a> to display the guessing block to the user.

<h3>Limitations</h3>
While the app works pretty well, if I do say so myself, there are a few limitations posed by Slack.

<h6>Gender</h6>
Unfortunately Slack doesn't tell males and females apart, which leads to female names being shown for male users and vice versa.

<h6>Minimal amount of users</h6>
The app requires at least 5 active users in your workspace to work, as it would make little sense otherwise.

<h6>Photo required</h6>
Only people who have uploaded their profile image can be shown. You wouldn't be able to guess them without their photos anyway.

<h6>Single workspace</h6>
The app is limited to only one Slack workspace. While we are aware there are some (particularly larger) companies that keep multiple Slack workspaces for different teams, Face Hunter (at least at this moment) doesn't support that.