<h1>Troubleshooting</h1>
<h5>Face Hunter - powered by <a href="https://www.roihunter.com/join-us">ROI Hunter</a></h5>
This file describes known issues and recommended way to solve them.

<h3>The `/facehunt` command does nothing</h3>
Make sure you run it in a public channel. Also check your Slack notifications - it's possible Face Hunter bot has already sent you a message, but you missed it (happened to us before).

<h3>Non-technical people in our company can't use slash commands</h3>
Yep, we've been there too. First I just sent an email explaining the slash command. When that wasn't sufficient, I had to go with a detailed description. Failing that, I had to resort to a screenshot. The ultimate solution is a make a step-by-step video.

<h3>A person isn't shown, even though he/she has a custom photo</h3>
This can happen with people who use Gravatar images. Afaik the only way to solve the issue is to ask them to upload the photo to Slack manually. The cause of the issue: when using Gravatar, Slack doesn't register it as a custom image, which is a prerequisite for the user being included in Face Hunter possible guesses.